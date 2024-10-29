package com.vti.transactionserver.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class

TransactionCreateForm {

    @NotEmpty(message = "Reference Id can not be a null or empty")
    private String referenceId;

    @NotEmpty(message = "Account number can not be a null or empty")
    private String accountNumber;

    @NotEmpty(message = "Transaction type can not be a null or empty")
    @Pattern(regexp = "DEPOSIT|WITHDRAWAL|INTERNAL_TRANSFER",
            message = "Transaction type must be DEPOSIT or WITHDRAWAL or INTERNAL_TRANSFER or EXTERNAL_TRANSFER")
    private String transactionType;

    private BigDecimal amount;

    @NotEmpty(message = "Status can not be a null or empty")
    @Pattern(regexp = "COMPLETED|PENDING", message = "Status must be COMPLETED or PENDING")
    private String status;

    @NotEmpty(message = "Comments can not be a null or empty")
    private String comments;
}
