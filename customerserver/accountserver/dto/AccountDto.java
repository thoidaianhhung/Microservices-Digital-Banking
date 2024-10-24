package com.vti.accounts.dto;

import com.vti.accounts.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDto {

    private String accountNumber;

    private Account.AccountType accountType;

    private String branchAddress;

    private Account.Status status;

    private LocalDateTime createDt;
}
