package com.aldebaran.auth.api;

import com.aldebaran.rest.error.event.ErrorEvent;


public enum AuthErrorEvents implements ErrorEvent {
    USER_LOCKED(1, "user.locked"),
    USER_DISABLED(2, "user.disabled"),
    USER_EXPIRED(3, "user.expired"),
    USER_CREDENTIALS_EXPIRED(4, "user.credentials.expired"),
    BAD_CREDENTIALS(5, "bad.credentials"),
    UNSUPPORTED_GRANT_TYPE(6, "unsupported.grant.type"),
    EMAIL_ALREADY_TAKEN(7, "email.already.taken"),
    USERNAME_ALREADY_TAKEN(8, "username.already.taken"),
    PASSWORDS_DO_NOT_MATCH(9, "passwords.do.not.match");

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