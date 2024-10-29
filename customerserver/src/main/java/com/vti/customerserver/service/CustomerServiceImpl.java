package com.vti.customerserver.service;


import com.vti.customerserver.dto.CustomerDto;
import com.vti.customerserver.entity.Customer;
import com.vti.customerserver.form.CustomerCreateForm;
import com.vti.customerserver.form.CustomerUpdateForm;
import com.vti.customerserver.mapper.CustomerMapper;
import com.vti.customerserver.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public Page<CustomerDto> findAllUsers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(CustomerMapper::map);
    }

    @Override
    public CustomerDto findByMobileNumber(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber);
        if (null == customer) {
            return null;
        }
        return CustomerMapper.map(customer);
    }

    @Override
    public CustomerDto createUser(CustomerCreateForm customerCreateForm) {
        var customer = CustomerMapper.map(customerCreateForm);
        customer.setRole(Customer.Role.ROLE_USER);
        var savedCustomer = customerRepository.save(customer);
        return CustomerMapper.map(savedCustomer);
    }


    @Override
    public CustomerDto updateUser(Long id, CustomerUpdateForm customerUpdateForm) {
        var optional = customerRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        var customer = optional.get();
        CustomerMapper.map(customerUpdateForm, customer);
        var savedCustomer = customerRepository.save(customer);
        return CustomerMapper.map(savedCustomer);
    }

    @Override
    public CustomerDto readCustomerById(Long customerId) {
        return customerRepository.findById(customerId).map(CustomerMapper::map).orElse(null);
    }
}
