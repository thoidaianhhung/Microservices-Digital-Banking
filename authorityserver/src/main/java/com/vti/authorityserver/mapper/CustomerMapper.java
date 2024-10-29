package com.vti.authorityserver.mapper;

import com.vti.authorityserver.dto.CustomerDto;
import com.vti.authorityserver.entity.Customer;
import com.vti.authorityserver.form.CustomerCreateForm;

public class CustomerMapper {
    private CustomerMapper() {

    }

    public static Customer map(CustomerCreateForm customerCreateForm) {
        var customer = new Customer();
        customer.setName(customerCreateForm.getName());
        customer.setEmail(customerCreateForm.getEmail());
        customer.setMobileNumber(customerCreateForm.getMobileNumber());
        customer.setPwd(customerCreateForm.getPwd());
        customer.setStatus(Customer.Status.valueOf(customerCreateForm.getStatus()));
        return customer;
    }

    public static CustomerDto map(Customer customer) {
        var dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setMobileNumber(customer.getMobileNumber());
        dto.setPwd(customer.getPwd());
        dto.setStatus(customer.getStatus());
        dto.setCreateDt(customer.getCreateDt());
        return dto;
    }
}
