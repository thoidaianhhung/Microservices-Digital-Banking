package com.vti.transactionserver.controller;

import com.vti.transactionserver.dto.TransactionDto;
import com.vti.transactionserver.form.TransactionCreateForm;
import com.vti.transactionserver.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Validated

public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Add transactions to the system.
     *
     * @param transactionCreateForm The transaction data to be added.
     * @return The response entity with the added transaction data.
     */
    @PostMapping("/transactions")
    public TransactionDto addTransaction(@RequestBody @Valid TransactionCreateForm transactionCreateForm) {
        return transactionService.addTransaction(transactionCreateForm);
    }


    /**
     * Retrieves a list of transactions for a given account ID.
     *
     * @param accountNumber The account number of the account
     * @return The list of transactions
     */
    @GetMapping("/accounts/{accountNumber}/transactions")
    public Page<TransactionDto> findTransactionByAccountNumber(@PathVariable("accountNumber") String accountNumber, Pageable pageable) {
        return transactionService.findTransactionByAccountId(accountNumber, pageable);
    }

    /**
     * Retrieves a list of transaction requests based on the provided transaction reference ID.
     *
     * @param referenceId The transaction reference ID
     * @return A ResponseEntity object containing the list of transaction requests
     */
    @GetMapping("/transactions/{referenceId}")
    public Page<TransactionDto> findTransactionByReferenceId(@PathVariable("referenceId") String referenceId, Pageable pageable) {
        return transactionService.findTransactionByTransactionReference(referenceId, pageable);
    }
}
