package com.vti.transferserver.external;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {

    private String referenceId;

    private String accountNumber;

    private TransactionType transactionType;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private TransactionStatus status;

    private String comments;

    public enum TransactionStatus {

        COMPLETED, PENDING
    }

    public enum TransactionType {

        DEPOSIT, WITHDRAWAL, INTERNAL_TRANSFER;
    }
}
