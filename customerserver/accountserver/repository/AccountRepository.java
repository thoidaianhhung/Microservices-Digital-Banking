package com.vti.accounts.repository;

import com.vti.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Find an account by user ID and account type.
     *
     * @param customerId  The ID of the user.
     * @param accountType The type of the account.
     * @return An optional containing the account if found, or empty if not found.
     */
    Optional<Account> findAccountByCustomerIdAndAccountType(Long customerId, Account.AccountType accountType);

    /**
     * Find an account by its account number.
     *
     * @param accountNumber The account number to search for.
     * @return An optional containing the found account, or an empty optional if no account was found.
     */
    Optional<Account> findAccountByAccountNumber(String accountNumber);

    /**
     * Finds an account by account ID.
     *
     * @param accountId the ID of the user
     * @return an optional account object
     */
    Optional<Account> findAccountByAccountId(Long accountId);
}
