package com.vti.transactionserver.dto;

import com.vti.transactionserver.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    private String referenceId;

    private String accountNumber;

    private String transactionType;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private Transaction.TransactionStatus status;

    private String comments;
}
