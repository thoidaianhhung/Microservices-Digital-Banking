package com.vti.transferserver.external;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountserver", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface AccountService {

    /**
     * Retrieves an account by account number.
     *
     * @param accountNumber The account number to search for.
     * @return The account matching the account number.
     */
    @GetMapping("/api/v1/accounts/{accountNumber}")
    AccountDto readByAccountNumber(@PathVariable("accountNumber") String accountNumber);

    /**
     * Updates an account with the given account number.
     *
     * @param accountNumber     The account number of the account to be updated.
     * @param accountUpdateForm The updated account details.
     * @return The response entity containing the response.
     */
    @PutMapping("/api/v1/accounts/{accountNumber}")
    Account updateBalance(@PathVariable("accountNumber") String accountNumber,
                          @RequestBody AccountUpdateForm accountUpdateForm);
}
