package com.vti.transactionserver.service;

import com.vti.transactionserver.dto.TransactionDto;
import com.vti.transactionserver.entity.Transaction;
import com.vti.transactionserver.exception.AccountStatusException;
import com.vti.transactionserver.exception.InsufficientBalance;
import com.vti.transactionserver.exception.ResourceNotFound;
import com.vti.transactionserver.external.AccountService;
import com.vti.transactionserver.external.AccountUpdateForm;
import com.vti.transactionserver.form.TransactionCreateForm;
import com.vti.transactionserver.mapper.TransactionMapper;
import com.vti.transactionserver.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public TransactionDto addTransaction(TransactionCreateForm transactionCreateForm) {
        var accountDto = accountService.readByAccountNumber(transactionCreateForm.getAccountNumber());
        if (accountDto == null) {
            throw new ResourceNotFound("Account not found on the server");
        }
        var transaction = TransactionMapper.map(transactionCreateForm);
        if (transaction.getTransactionType().equals(Transaction.TransactionType.DEPOSIT)) {
            accountDto.setBalance(accountDto.getBalance().add(transaction.getAmount()));
        } else if (transaction.getTransactionType().equals(Transaction.TransactionType.WITHDRAWAL)) {
            if (!accountDto.getAccountType().toString().equals("ACTIVE")) {
                log.error("account is either inactive/closed, cannot process the transaction");
                throw new AccountStatusException("account is inactive or closed");
            }
            if (accountDto.getBalance().compareTo(transaction.getAmount()) < 0) {
                log.error("insufficient balance in the account");
                throw new InsufficientBalance("Insufficient balance in the account");
            }
            transaction.setAmount(transaction.getAmount().negate());
            accountDto.setBalance(accountDto.getBalance().subtract(transaction.getAmount()));
        }
        transaction.setStatus(Transaction.TransactionStatus.COMPLETED);
        var accountUpdateForm = new AccountUpdateForm();
        accountUpdateForm.setAccountNumber(transactionCreateForm.getAccountNumber());
        accountUpdateForm.setAccountType(accountDto.getAccountType().toString());
        accountUpdateForm.setBranchAddress(accountDto.getBranchAddress());
        accountUpdateForm.setStatus(accountDto.getStatus().toString());
        accountUpdateForm.setBalance(accountDto.getBalance());
        accountService.updateBalance(transactionCreateForm.getAccountNumber(), accountUpdateForm);
        var savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.map(savedTransaction);
    }

    @Override
    public Page<TransactionDto> findTransactionByAccountId(String accountNumber, Pageable pageable) {
        return transactionRepository.findTransactionByAccountNumber(accountNumber, pageable).map(TransactionMapper::map);
    }

    @Override
    public Page<TransactionDto> findTransactionByTransactionReference(String referenceId, Pageable pageable) {
        return transactionRepository.findTransactionByReferenceId(referenceId, pageable).map(TransactionMapper::map);
    }
}
