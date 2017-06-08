package com.aldebaran.auth;

import com.aldebaran.rest.error.codes.ErrorEvent;


public enum AuthErrorEvents implements ErrorEvent {
    USER_LOCKED(1, "user.locked"),
    USER_DISABLED(2, "user.disabled"),
    USER_EXPIRED(3, "user.expired"),
    USER_CREDENTIALS_EXPIRED(4, "user.credentials.expired"),
    BAD_CREDENTIALS(5, "bad.credentials");

    private final static int SUB_CODE = 10;

    private final int errorCode;
    private final int errorSubCode;
    private final String errorMessageKey;

    AuthErrorEvents(int errorCode, String errorMessageKey) {
        this.errorCode = errorCode;
        this.errorSubCode = SUB_CODE;
        this.errorMessageKey = errorMessageKey;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public int getErrorSubCode() {
        return SUB_CODE;
    }

    @Override
    public String getErrorMessageKey() {
        return errorMessageKey;
    }
}