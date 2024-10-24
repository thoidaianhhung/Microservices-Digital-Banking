package com.vti.accounts.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "transaction-service", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface TransactionService {

    /**
     * Retrieves a list of transactions from the specified account ID.
     *
     * @param accountId the ID of the account
     * @return a list of transaction responses
     */
    @GetMapping("/transactions")
    Page<TransactionResponse> getTransactionsFromAccountId(@RequestParam String accountId, Pageable pageable);
}
