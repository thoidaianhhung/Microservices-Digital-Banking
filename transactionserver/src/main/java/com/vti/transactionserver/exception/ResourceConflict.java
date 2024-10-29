package com.vti.transactionserver.exception;

public class ResourceConflict extends GlobalException {

    public ResourceConflict() {
        super("Account already exists", GlobalErrorCode.CONFLICT);
    }

    public ResourceConflict(String message) {
        super(message, GlobalErrorCode.CONFLICT);
    }
}