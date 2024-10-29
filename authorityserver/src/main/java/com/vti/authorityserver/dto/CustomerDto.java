package com.vti.authorityserver.dto;

import com.vti.authorityserver.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class CustomerDto {

    private String name;

    private String email;

    private String mobileNumber;

    private String pwd;

    private Customer.Status status;

    private LocalDateTime createDt;
}
