package com.vti.transferserver.exception;

public class InsufficientBalance extends GlobalException {
    public InsufficientBalance(String errorCode, String message) {
        super(errorCode, message);
    }
}
