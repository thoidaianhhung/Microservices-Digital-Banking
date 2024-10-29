package com.vti.accountserver.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "customerserver", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface CustomerService {

    /**
     * Retrieves a user by their ID.
     *
     * @param customerId the ID of the user to retrieve
     * @return a ResponseEntity containing the user DTO if found, or an empty body with a not found status code
     */
    @GetMapping("/api/v1/customers/{id}")
    CustomerDto readCustomerById(@PathVariable("id") Long customerId);
}
