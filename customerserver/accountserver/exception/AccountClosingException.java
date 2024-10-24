package com.vti.accounts.exception;

public class AccountClosingException extends GlobalException {

    public AccountClosingException(String errorMessage) {
        super(GlobalErrorCode.BAD_REQUEST, errorMessage);
    }
}
