package com.vti.customerserver.external;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto {

    private Long accountId;

    private Long accountNumber;

    private AccountType accountType;

    private String branchAddress;

    private Status status;

    private LocalDateTime createDt;

    public enum Status {
        OPENING, ACTIVE, CLOSED
    }

    public enum AccountType {
        SAVINGS_ACCOUNT, LOAN_ACCOUNT
    }
}
