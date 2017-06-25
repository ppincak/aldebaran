package com.aldebaran.rest.error;

import com.aldebaran.rest.error.event.ErrorEvent;


public enum GeneralErrorEvents implements ErrorEvent {

    INTERNAL_SERVER_ERROR(1, "internal.server.error"),
    RESOURCE_NOT_FOUND(2, "resource.not found"),
    UNAUTHORIZED(4, "unauthorized.access"),
    UNSUPPORTED_SEARCH_OPERATOR(4, "unsupported.search.operator"),
    NOT_FOUND(5, "not.found"),
    NOT_ALLOWED(6, "not.allowed"),
    INVALID_API_KEY(7, "invalid.api.key");

    private final static int SUB_CODE = 3;

    private final int errorCode;
    private final int errorSubCode;
    private final String errorMessageKey;

    GeneralErrorEvents(int errorCode, String errorMessageKey) {
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