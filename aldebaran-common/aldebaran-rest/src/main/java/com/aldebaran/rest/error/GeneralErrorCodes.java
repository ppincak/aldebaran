package com.aldebaran.rest.error;

import com.aldebaran.rest.error.codes.ErrorEvent;


public enum GeneralErrorCodes implements ErrorEvent {

    INTERNAL_SERVER_ERROR(1, "internal.server.error"),
    RESOURCE_NOT_FOUND(2, "resource.not found"),
    UNSUPPORTED_SEARCH_OPERATOR(3, "unsupported.search.operator");

    private final static int SUB_CODE = 3;

    private final int errorCode;
    private final int errorSubCode;
    private final String errorMessageKey;

    GeneralErrorCodes(int errorCode, String errorMessageKey) {
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