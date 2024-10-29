package com.vti.transferserver.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferUpdateForm {

    @NotEmpty(message = "From account can not be a null or empty")
    private String fromAccount;

    @NotEmpty(message = "To account can not be a null or empty")
    private String toAccount;

    private BigDecimal amount;

    @NotEmpty(message = "Status can not be a null or empty")
    @Pattern(regexp = "PENDING|PROCESSING|SUCCESS|FAILED", message = "Status must be COMPLETED or PENDING")
    private String status;

    @NotEmpty(message = "Transfer type can not be a null or empty")
    @Pattern(regexp = "WITHDRAWAL|INTERNAL|EXTERNAL|CHEQUE",
            message = "Transfer type must be DEPOSIT or WITHDRAWAL or INTERNAL_TRANSFER or EXTERNAL_TRANSFER")
    private String transferType;
}
