package com.vti.accountserver.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class

AccountCreateForm {
    @NotEmpty(message = "Account number can not be a null or empty")
    @Pattern(regexp = "(^$|\\d{10})", message = "AccountNumber must be 10 digits")
    private String accountNumber;

    @NotEmpty(message = "Account type can not be a null or empty")
    @Pattern(regexp = "SAVINGS_ACCOUNT|LOANS_ACCOUNT", message = "Account type must be S or C")
    private String accountType;

    @NotEmpty(message = "Branch address can not be a null or empty")
    @Size(min = 5, max = 150, message = "The length of the customer name should be between 5 and 30")
    private String branchAddress;

    @Pattern(regexp = "OPENING|ACTIVE|CLOSED", message = "Customer status must be OPENING or CLOSED")
    private String status;
}
