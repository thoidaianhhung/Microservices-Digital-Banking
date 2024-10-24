package com.vti.customerserver.mapper;

import com.vti.customerserver.dto.CustomerDto;
import com.vti.customerserver.entity.Customer;
import com.vti.customerserver.form.CustomerCreateForm;
import com.vti.customerserver.form.CustomerUpdateForm;

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

    public static void map(CustomerUpdateForm customerUpdateForm, Customer customer) {
        customer.setName(customerUpdateForm.getName());
        customer.setEmail(customerUpdateForm.getEmail());
        customer.setMobileNumber(customerUpdateForm.getMobileNumber());
        customer.setPwd(customerUpdateForm.getPwd());
        customer.setStatus(Customer.Status.valueOf(customerUpdateForm.getStatus()));
    }
}
