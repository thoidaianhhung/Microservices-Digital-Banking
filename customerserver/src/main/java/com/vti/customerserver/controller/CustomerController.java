package com.vti.customerserver.controller;

import com.vti.customerserver.dto.CustomerDto;
import com.vti.customerserver.form.CustomerCreateForm;
import com.vti.customerserver.form.CustomerUpdateForm;
import com.vti.customerserver.service.CustomerService;
import com.vti.customerserver.validation.CustomerIdExists;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/customers")
    public Page<CustomerDto> findAllUsers(Pageable pageable) {
        return customerService.findAllUsers(pageable);
    }


    @GetMapping("/customers/mobile/{mobileNumber}")
    public CustomerDto findByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) {

        return customerService.findByMobileNumber(mobileNumber);
    }

    @PostMapping("/register")
    public CustomerDto createUser(@Valid @RequestBody CustomerCreateForm customerCreateForm) {
        return customerService.createUser(customerCreateForm);
    }


    @PutMapping("/customers/{id}")
    public CustomerDto updateUser(@PathVariable("id") @CustomerIdExists Long id,
                                  @Valid @RequestBody CustomerUpdateForm customerUpdateForm) {

        return customerService.updateUser(id, customerUpdateForm);
    }

    @GetMapping("/customers/{customerId}")
    public CustomerDto readCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.readCustomerById(customerId);
    }
}
