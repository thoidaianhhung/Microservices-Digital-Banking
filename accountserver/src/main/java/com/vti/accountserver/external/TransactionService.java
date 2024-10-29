package com.vti.accountserver.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "transactionserver", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface TransactionService {

    /**
     * Retrieves a list of transactions from the specified account ID.
     *
     * @param accountNumber the account number of the account
     * @return a list of transaction responses
     */
    @GetMapping("api/v1/accounts/{accountNumber}/transactions")
    Page<TransactionResponse> getTransactionsFromAccountId(@PathVariable("accountNumber") String accountNumber, Pageable pageable);
}
