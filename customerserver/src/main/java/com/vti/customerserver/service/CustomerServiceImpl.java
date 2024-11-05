package com.vti.customerserver.service;


import com.vti.customerserver.constants.ApplicationConstants;
import com.vti.customerserver.dto.CustomerDto;
import com.vti.customerserver.dto.LoginResponseDTO;
import com.vti.customerserver.entity.Customer;
import com.vti.customerserver.form.CustomerCreateForm;
import com.vti.customerserver.form.CustomerUpdateForm;
import com.vti.customerserver.form.LoginRequestForm;
import com.vti.customerserver.mapper.CustomerMapper;
import com.vti.customerserver.repository.AuthorityRepository;
import com.vti.customerserver.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final AuthenticationManager authenticationManager;
    private final Environment env;
    private CustomerRepository customerRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;

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
        customer.setRole(Customer.Role.ROLE_CUSTOMER);
        customer.setPwd(passwordEncoder.encode(customerCreateForm.getPwd()));
        var savedCustomer = customerRepository.save(customer);
        return CustomerMapper.map(savedCustomer);
    }
    @Override
    public ResponseEntity<LoginResponseDTO> loginUser(LoginRequestForm loginRequestForm) {
        String jwt = "";
        org.springframework.security.core.Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequestForm.username(),
                loginRequestForm.password());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if (null != authenticationResponse && authenticationResponse.isAuthenticated() && null != env) {
            String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                    ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            jwt = Jwts.builder().issuer("VTI Bank").subject("JWT Token")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                            GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                    .issuedAt(new java.util.Date())
                    .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                    .signWith(secretKey).compact();
        }
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, jwt)
                .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
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
