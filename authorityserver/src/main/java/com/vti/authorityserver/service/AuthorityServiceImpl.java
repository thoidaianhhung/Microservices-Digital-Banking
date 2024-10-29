package com.vti.authorityserver.service;

import com.vti.authorityserver.constants.ApplicationConstants;
import com.vti.authorityserver.dto.CustomerDto;
import com.vti.authorityserver.dto.LoginResponseDTO;
import com.vti.authorityserver.entity.Authority;
import com.vti.authorityserver.entity.Customer;
import com.vti.authorityserver.form.CustomerCreateForm;
import com.vti.authorityserver.form.LoginRequestForm;
import com.vti.authorityserver.mapper.CustomerMapper;
import com.vti.authorityserver.repository.AuthorityRepository;
import com.vti.authorityserver.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
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
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthenticationManager authenticationManager;
    private final Environment env;
    private CustomerRepository customerRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public CustomerDto createUser(CustomerCreateForm customerCreateForm) {
        var customer = CustomerMapper.map(customerCreateForm);
        var endCodePwd = passwordEncoder.encode(customerCreateForm.getPwd());
        customer.setPwd(endCodePwd);
        customer.setRole(Customer.Role.ROLE_USER);
        var savedCustomer = customerRepository.save(customer);
        var authority = new Authority();
        authority.setAuthorityName(String.valueOf(Customer.Role.ROLE_USER));
        authority.setCustomer(customer);
        authorityRepository.save(authority);
        return CustomerMapper.map(savedCustomer);
    }

    @Override
    public ResponseEntity<LoginResponseDTO> loginUser(LoginRequestForm loginRequestForm) {
        String jwt = "";
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequestForm.username(),
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
}
