package com.vti.accountserver.service;

import com.vti.accountserver.dto.AccountDto;
import com.vti.accountserver.entity.Account;
import com.vti.accountserver.exception.AccountClosingException;
import com.vti.accountserver.exception.ResourceConflict;
import com.vti.accountserver.exception.ResourceNotFound;
import com.vti.accountserver.external.CustomerService;
import com.vti.accountserver.external.TransactionResponse;
import com.vti.accountserver.external.TransactionService;
import com.vti.accountserver.form.AccountCreateForm;
import com.vti.accountserver.form.AccountUpdateForm;
import com.vti.accountserver.mapper.AccountMapper;
import com.vti.accountserver.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final CustomerService customerService;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Override
    public AccountDto createAccount(Long customerId, AccountCreateForm accountCreateForm) {
        var customerDto = customerService.readCustomerById(customerId);
        if (customerDto == null) {
            throw new ResourceNotFound("Customer not found on the server");
        }
        accountRepository.findAccountByCustomerIdAndAccountType(customerId, Account.AccountType.valueOf(accountCreateForm.getAccountType()))
                .ifPresent(account -> {
                    log.error("Account already exists on the server");
                    throw new ResourceConflict("Account already exists on the server");
                });
        var account = AccountMapper.map(accountCreateForm);
        account.setCustomerId(customerId);
        account.setBalance(BigDecimal.ZERO);
        var savedAccount = accountRepository.save(account);
        return AccountMapper.map(savedAccount);
    }

    @Override
    public AccountDto readAccountByAccountNumber(String accountNumber) {

        return accountRepository.findAccountByAccountNumber(accountNumber)
                .map(AccountMapper::map)
                .orElseThrow(ResourceNotFound::new);
    }

    @Override
    public AccountDto updateAccount(String accountNumber, AccountUpdateForm accountUpdateForm) {
        var optionalAccount = accountRepository.findAccountByAccountNumber(accountNumber);
        if (optionalAccount.isPresent()) {
            var account = optionalAccount.get();
            AccountMapper.map(accountUpdateForm, account);
            var savedAccount = accountRepository.save(account);
            return AccountMapper.map(savedAccount);
        }
        return null;
    }


    @Override
    public Page<AccountDto> findByCustomerId(Long customerId, Pageable pageable) {
        return accountRepository.findByCustomerId(customerId, pageable).map(AccountMapper::map);
    }

    @Override
    public String getBalance(String accountNumber) {

        return accountRepository.findAccountByAccountNumber(accountNumber)
                .map(account -> account.getBalance().toString())
                .orElseThrow(ResourceNotFound::new);
    }


    @Override
    public Page<TransactionResponse> getTransactionsFromAccountId(String accountId, Pageable pageable) {
        return transactionService.getTransactionsFromAccountId(accountId, pageable);
    }


    @Override
    public AccountDto closeAccount(String accountNumber) {

        return accountRepository.findAccountByAccountNumber(accountNumber)
                .map(account -> {
                    if (BigDecimal.valueOf(Double.parseDouble(getBalance(accountNumber))).compareTo(BigDecimal.ZERO) != 0) {
                        throw new AccountClosingException("Balance should be zero");
                    }
                    account.setStatus(Account.Status.CLOSED);
                    var savedAccount = accountRepository.save(account);
                    return AccountMapper.map(savedAccount);
                }).orElseThrow(ResourceNotFound::new);

    }
}
