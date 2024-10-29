package com.vti.transferserver.external;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Account {

    private Long accountId;

    private String accountNumber;

    private AccountType accountType;

    private String branchAddress;

    private Status status;

    private LocalDateTime createDt;

    private BigDecimal balance;

    private Long customerId;

    public enum Status {
        OPENING, ACTIVE, CLOSED
    }

    public enum AccountType {
        SAVINGS_ACCOUNT, LOANS_ACCOUNT
    }
}
