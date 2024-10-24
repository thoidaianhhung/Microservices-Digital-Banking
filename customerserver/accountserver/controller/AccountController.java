package com.vti.accounts.controller;

import com.vti.accounts.dto.AccountDto;
import com.vti.accounts.external.TransactionResponse;
import com.vti.accounts.form.AccountCreateForm;
import com.vti.accounts.form.AccountUpdateForm;
import com.vti.accounts.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/customers/{customerId}/accounts")
    public AccountDto createAccount(@PathVariable("customerId") Long customerId,
                                    @RequestBody @Valid AccountCreateForm accountCreateForm) {
        return accountService.createAccount(customerId, accountCreateForm);
    }


    @GetMapping("/accounts/{accountNumber}")
    public AccountDto readAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return accountService.readAccountByAccountNumber(accountNumber);
    }

    @PutMapping("/accounts/{accountNumber}")
    public AccountDto updateAccount(@PathVariable("accountNumber") String accountNumber,
                                    @RequestBody @Valid AccountUpdateForm accountUpdateForm) {
        return accountService.updateAccount(accountNumber, accountUpdateForm);
    }

    @GetMapping("/accounts/{accountNumber}/balance")
    public String getBalance(@PathVariable("accountNumber") String accountNumber) {
        return accountService.getBalance(accountNumber);
    }

    @GetMapping("/accounts/{accountNumber}/transactions")
    public Page<TransactionResponse> getTransactionsFromAccountId(
            @PathVariable("accountNumber") String accountId, Pageable pageable) {
        return accountService.getTransactionsFromAccountId(accountId, pageable);
    }

    @PostMapping("/accounts/{accountNumber}/close")
    public void closeAccount(@PathVariable("accountNumber") String accountNumber) {
        accountService.closeAccount(accountNumber);
    }

}
