package com.aldebaran.rest.error;

import com.aldebaran.rest.error.codes.ErrorEvent;


public enum ValidationErrorCodes implements ErrorEvent {

    VALIDATION_ERROR(1, "validation.error"),
    NOT_NULL_ERROR(2, "not.null.error"),
    NOT_EMPTY_ERROR(3, "not.empty.error"),
    INVALID_EMAIL_ADDRESS(4, "invalid.email.address");

    private final static int SUB_CODE = 2;

    private final int errorCode;
    private final int errorSubCode;
    private final String errorMessageKey;

    ValidationErrorCodes(int errorCode, String errorMessageKey) {
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
        return errorSubCode;
    }

    @Override
    public String getErrorMessageKey() {
        return errorMessageKey;
    }
}
