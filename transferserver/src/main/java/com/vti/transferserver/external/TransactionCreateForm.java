package com.vti.transferserver.external;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class

TransactionCreateForm {


    private String referenceId;

    private String accountNumber;

    private String transactionType;

    private BigDecimal amount;

    private String status;

    private String comments;
}
