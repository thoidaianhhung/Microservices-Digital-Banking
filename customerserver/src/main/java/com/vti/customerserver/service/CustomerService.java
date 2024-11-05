package com.vti.customerserver.service;


import com.vti.customerserver.dto.CustomerDto;
import com.vti.customerserver.dto.LoginResponseDTO;
import com.vti.customerserver.form.CustomerCreateForm;
import com.vti.customerserver.form.CustomerUpdateForm;
import com.vti.customerserver.form.LoginRequestForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {


    /**
     * Retrieves a list of all users.
     *
     * @return a list of CustomerDto objects representing all users
     */
    Page<CustomerDto> findAllUsers(Pageable pageable);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return User Details based on a given mobileNumber
     */
    CustomerDto findByMobileNumber(String mobileNumber);


    CustomerDto createUser(CustomerCreateForm customerCreateForm);

    ResponseEntity<LoginResponseDTO> loginUser(LoginRequestForm loginRequestForm);


    /**
     * @param id, customerUpdateForm - CustomerDto Object
     * @return update of User details
     */
    CustomerDto updateUser(Long id, CustomerUpdateForm customerUpdateForm);

    CustomerDto readCustomerById(Long customerId);
}
