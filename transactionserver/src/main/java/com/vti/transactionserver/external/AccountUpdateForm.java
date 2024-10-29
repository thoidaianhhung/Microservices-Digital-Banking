package com.vti.transactionserver.external;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountUpdateForm {

    private String accountNumber;

    private String accountType;

    private String branchAddress;

    private String status;

    private BigDecimal balance;
}
