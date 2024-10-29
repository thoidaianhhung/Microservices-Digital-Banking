package com.vti.transferserver.external;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDto {

    private String referenceId;

    private String accountNumber;

    private String transactionType;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private Transaction.TransactionStatus status;

    private String comments;
}
