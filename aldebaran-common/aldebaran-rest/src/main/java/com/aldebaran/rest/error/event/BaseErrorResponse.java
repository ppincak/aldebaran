package com.aldebaran.rest.error.event;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BaseErrorResponse {

    @JsonProperty
    private Long errorCode;

    @JsonProperty
    private String errorKey;

    @JsonProperty
    private String errorMessage;

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessageKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}