package com.vti.accountserver.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private String referenceId;

    private String accountId;

    private String transactionType;

    private Long amount;

    private LocalDateTime localDateTime;

    private String transactionStatus;

    private String comments;
}
