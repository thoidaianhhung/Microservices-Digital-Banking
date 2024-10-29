package com.vti.transferserver.dto;

import com.vti.transferserver.entity.Transfer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransferDto {

    private String referenceId;

    private String fromAccount;

    private String toAccount;

    private BigDecimal amount;

    private Transfer.TransactionStatus status;

    private Transfer.TransferType transferType;

    private LocalDateTime transferredOn;
}
