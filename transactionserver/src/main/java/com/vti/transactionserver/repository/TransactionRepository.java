package com.vti.transactionserver.repository;


import com.vti.transactionserver.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Finds transactions by account ID.
     *
     * @param accountNumber the ID of the account
     * @return a list of transactions
     */
    Page<Transaction> findTransactionByAccountNumber(String accountNumber, Pageable pageable);

    /**
     * Returns a list of transactions that match the given reference ID.
     *
     * @param referenceId The reference ID to match against.
     * @return The list of transactions that match the reference ID.
     */
    Page<Transaction> findTransactionByReferenceId(String referenceId, Pageable pageable);
}
