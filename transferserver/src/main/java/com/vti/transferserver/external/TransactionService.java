package com.vti.transferserver.external;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "transactionserver", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface TransactionService {

    /**
     * Endpoint to make a transaction.
     *
     * @param transactionCreateForm The transaction object containing the details of the transaction.
     * @return The ResponseEntity containing the response of the transaction.
     */
    @PostMapping("api/v1/transactions")
    public TransactionDto addTransaction(@RequestBody TransactionCreateForm transactionCreateForm);
}
