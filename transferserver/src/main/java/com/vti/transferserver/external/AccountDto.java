package com.vti.transferserver.external;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto {

    private String accountNumber;

    private Account.AccountType accountType;

    private String branchAddress;

    private Account.Status status;

    private LocalDateTime createDt;

    private BigDecimal balance;
}
