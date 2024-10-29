package com.vti.accountserver.external;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDto {
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;

    private Status status;

    private LocalDateTime createDt;

    public enum Status {
        OPENING, CLOSED
    }

    public enum Role {
        ROLE_ADMIN, ROLE_USER;
    }
}
