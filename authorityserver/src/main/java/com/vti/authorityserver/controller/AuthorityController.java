package com.vti.authorityserver.controller;

import com.vti.authorityserver.dto.CustomerDto;
import com.vti.authorityserver.dto.LoginResponseDTO;
import com.vti.authorityserver.form.CustomerCreateForm;
import com.vti.authorityserver.form.LoginRequestForm;
import com.vti.authorityserver.service.AuthorityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class AuthorityController {

    private final AuthorityService customerService;

    @PostMapping("/register")
    public CustomerDto createUser(@Valid @RequestBody CustomerCreateForm customerCreateForm) {
        return customerService.createUser(customerCreateForm);
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestForm loginRequestForm) {
        return customerService.loginUser(loginRequestForm);
    }
}
