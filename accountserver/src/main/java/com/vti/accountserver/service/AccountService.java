package com.vti.accountserver.service;

import com.vti.accountserver.dto.AccountDto;
import com.vti.accountserver.external.TransactionResponse;
import com.vti.accountserver.form.AccountCreateForm;
import com.vti.accountserver.form.AccountUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    AccountDto createAccount(Long customerId, AccountCreateForm accountCreateForm);


    /**
     * Retrieves an account by its account number.
     *
     * @param accountNumber The account number to search for.
     * @return The account DTO if found, or null if not found.
     */
    AccountDto readAccountByAccountNumber(String accountNumber);


    AccountDto updateAccount(String accountNumber, AccountUpdateForm accountUpdateForm);


    Page<AccountDto> findByCustomerId(Long customerId, Pageable pageable);

    /**
     * Retrieves the balance of the account with the specified account number.
     *
     * @param accountNumber The account number for which to retrieve the balance.
     * @return The balance of the account as a string.
     */
    String getBalance(String accountNumber);


    /**
     * Retrieves a list of transaction responses from the specified account ID.
     *
     * @param accountId The ID of the account to retrieve transactions from.
     * @return A list of transaction responses.
     */
    Page<TransactionResponse> getTransactionsFromAccountId(String accountId, Pageable pageable);

    /**
     * Closes the account with the specified account number.
     *
     * @param accountNumber The account number of the account to be closed.
     * @return The response indicating the result of the account closure.
     */
    AccountDto closeAccount(String accountNumber);
}
