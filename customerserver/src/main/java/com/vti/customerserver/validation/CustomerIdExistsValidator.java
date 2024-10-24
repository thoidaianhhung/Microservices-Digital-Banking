package com.vti.customerserver.validation;

import com.vti.customerserver.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerIdExistsValidator implements ConstraintValidator<CustomerIdExists, Long> {

    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return customerRepository.existsById(id);
    }
}
