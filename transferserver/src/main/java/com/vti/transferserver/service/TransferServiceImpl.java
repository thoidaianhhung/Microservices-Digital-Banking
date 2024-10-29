package com.vti.transferserver.service;

import com.vti.transferserver.dto.TransferDto;
import com.vti.transferserver.exception.AccountUpdateException;
import com.vti.transferserver.exception.GlobalErrorCode;
import com.vti.transferserver.exception.InsufficientBalance;
import com.vti.transferserver.exception.ResourceNotFound;
import com.vti.transferserver.external.*;
import com.vti.transferserver.form.TransferCreateForm;
import com.vti.transferserver.mapper.TransferMapper;
import com.vti.transferserver.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountService accountService;
    private final TransferRepository fundTransferRepository;
    private final TransactionService transactionService;


    /**
     * Transfers funds from one account to another.
     *
     * @param transferCreateForm The request object containing the details of the fund transfer.
     * @return The response object indicating the status of the fund transfer.
     * @throws ResourceNotFound       If the requested account is not found on the server.
     * @throws AccountUpdateException If the account status is pending or inactive.
     * @throws InsufficientBalance    If the required amount to transfer is not available.
     */
    @Override
    public TransferDto createTransfer(TransferCreateForm transferCreateForm) {
        var fromAccount = accountService.readByAccountNumber(transferCreateForm.getFromAccount());
        if (fromAccount == null) {
            log.error("requested account " + transferCreateForm.getFromAccount() + " is not found on the server");
            throw new ResourceNotFound("Account not found on the server", GlobalErrorCode.NOT_FOUND);
        }
        if (!fromAccount.getStatus().toString().equals("ACTIVE")) {
            log.error("account status is pending or inactive, please update the account status");
            throw new AccountUpdateException("account is status is :pending", GlobalErrorCode.NOT_ACCEPTABLE);
        }
        if (fromAccount.getBalance().compareTo(transferCreateForm.getAmount()) < 0) {
            log.error("required amount to transfer is not available");
            throw new InsufficientBalance("requested amount is not available", GlobalErrorCode.NOT_ACCEPTABLE);
        }

        var toAccount = accountService.readByAccountNumber(transferCreateForm.getToAccount());
        if (toAccount == null) {
            log.error("requested account " + transferCreateForm.getToAccount() + " is not found on the server");
            throw new ResourceNotFound("requested account not found on the server", GlobalErrorCode.NOT_FOUND);
        }
        var transfer = TransferMapper.map(transferCreateForm);
        transfer.setReferenceId(internalTransfer(fromAccount, toAccount, transferCreateForm.getAmount()));
        var savedTransfer = fundTransferRepository.save(transfer);
        return TransferMapper.map(savedTransfer);
    }

    /**
     * Transfers funds from one account to another within the system.
     *
     * @param fromAccount The account to transfer funds from.
     * @param toAccount   The account to transfer funds to.
     * @param amount      The amount of funds to transfer.
     * @return The transaction reference number.
     */
    private String internalTransfer(AccountDto fromAccount, AccountDto toAccount, BigDecimal amount) {
        String transactionReference = UUID.randomUUID().toString();

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        var formAccountUpdateForm = new AccountUpdateForm();
        formAccountUpdateForm.setAccountNumber(fromAccount.getAccountNumber());
        formAccountUpdateForm.setAccountType(fromAccount.getAccountType().toString());
        formAccountUpdateForm.setBranchAddress(fromAccount.getBranchAddress());
        formAccountUpdateForm.setStatus(Account.Status.ACTIVE.toString());
        formAccountUpdateForm.setBalance(fromAccount.getBalance());

        var formTransactionCreateForm = new TransactionCreateForm();
        formTransactionCreateForm.setReferenceId(transactionReference);
        formTransactionCreateForm.setAccountNumber(fromAccount.getAccountNumber());
        formTransactionCreateForm.setTransactionType(Transaction.TransactionType.INTERNAL_TRANSFER.toString());
        formTransactionCreateForm.setAmount(amount);
        formTransactionCreateForm.setStatus(Transaction.TransactionStatus.COMPLETED.toString());
        formTransactionCreateForm.setComments("Fund transfer from " + fromAccount.getAccountNumber());

        accountService.updateBalance(formTransactionCreateForm.getAccountNumber(), formAccountUpdateForm);

        toAccount.setBalance(toAccount.getBalance().add(amount));
        var toAccountUpdateForm = new AccountUpdateForm();
        toAccountUpdateForm.setAccountNumber(toAccount.getAccountNumber());
        toAccountUpdateForm.setAccountType(toAccount.getAccountType().toString());
        toAccountUpdateForm.setBranchAddress(toAccount.getBranchAddress());
        toAccountUpdateForm.setStatus(Account.Status.ACTIVE.toString());
        toAccountUpdateForm.setBalance(toAccount.getBalance());

        var toTransactionCreateForm = new TransactionCreateForm();
        toTransactionCreateForm.setReferenceId(transactionReference);
        toTransactionCreateForm.setAccountNumber(toAccount.getAccountNumber());
        toTransactionCreateForm.setTransactionType(Transaction.TransactionType.INTERNAL_TRANSFER.toString());
        toTransactionCreateForm.setAmount(amount);
        toTransactionCreateForm.setStatus(Transaction.TransactionStatus.COMPLETED.toString());
        toTransactionCreateForm.setComments("Fund transfer from " + toAccount.getAccountNumber());

        accountService.updateBalance(toTransactionCreateForm.getAccountNumber(), toAccountUpdateForm);

        transactionService.addTransaction(formTransactionCreateForm);
        transactionService.addTransaction(toTransactionCreateForm);

        return transactionReference;
    }

    /**
     * Retrieves the details of a fund transfer based on the given reference ID.
     *
     * @param referenceId The reference ID of the fund transfer.
     * @return The FundTransferDto containing the details of the fund transfer.
     * @throws ResourceNotFound if the fund transfer is not found.
     */
    @Override
    public TransferDto getTransferDetailsFromReferenceId(String referenceId) {

        return fundTransferRepository.findTransferByReferenceId(referenceId)
                .map(TransferMapper::map)
                .orElseThrow(() -> new ResourceNotFound("Fund transfer not found", GlobalErrorCode.NOT_FOUND));
    }

    /**
     * Retrieves a list of fund transfers associated with the given account ID.
     *
     * @param accountNumber The Account Number of the account
     * @return A list of fund transfer DTOs
     */
    @Override
    public Page<TransferDto> findTransferByAccountNumber(String accountNumber, Pageable pageable) {
        return fundTransferRepository.findTransferByFromAccount(accountNumber, pageable)
                .map(TransferMapper::map);
    }
}
