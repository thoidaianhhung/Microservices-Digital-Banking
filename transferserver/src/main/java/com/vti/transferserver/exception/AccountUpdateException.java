package com.vti.transferserver.exception;

public class AccountUpdateException extends GlobalException {
    public AccountUpdateException(String errorCode, String message) {
        super(errorCode, message);
    }
}
