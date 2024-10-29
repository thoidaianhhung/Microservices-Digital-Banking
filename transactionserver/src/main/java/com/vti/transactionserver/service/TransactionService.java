package com.vti.transactionserver.service;


import com.vti.transactionserver.dto.TransactionDto;
import com.vti.transactionserver.form.TransactionCreateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    /**
     * Adds a transaction.
     *
     * @param transactionCreateForm The transaction to add.
     * @return The response indicating whether the transaction was successfully added.
     */
    TransactionDto addTransaction(TransactionCreateForm transactionCreateForm);


    /**
     * Retrieves a list of transaction requests for a given account ID.
     *
     * @param accountNumber the ID of the account
     * @return a list of transaction requests
     */
    Page<TransactionDto> findTransactionByAccountId(String accountNumber, Pageable pageable);

    /**
     * Retrieves a list of transaction requests by transaction reference.
     *
     * @param transactionReference The transaction reference to search for.
     * @return A list of transaction requests matching the given transaction reference.
     */
    Page<TransactionDto> findTransactionByTransactionReference(String referenceId, Pageable pageable);


}
