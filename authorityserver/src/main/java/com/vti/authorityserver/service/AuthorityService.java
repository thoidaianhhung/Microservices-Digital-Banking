package com.vti.authorityserver.service;


import com.vti.authorityserver.dto.CustomerDto;
import com.vti.authorityserver.dto.LoginResponseDTO;
import com.vti.authorityserver.form.CustomerCreateForm;
import com.vti.authorityserver.form.LoginRequestForm;
import org.springframework.http.ResponseEntity;

public interface AuthorityService {


    CustomerDto createUser(CustomerCreateForm customerCreateForm);


    ResponseEntity<LoginResponseDTO> loginUser(LoginRequestForm loginRequestForm);

}
