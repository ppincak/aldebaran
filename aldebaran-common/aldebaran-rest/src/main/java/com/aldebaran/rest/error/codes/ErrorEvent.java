package com.aldebaran.rest.error.codes;

import java.util.Collections;


public interface ErrorEvent {

    int getErrorCode();

    int getErrorSubCode();

    String getErrorMessageKey();

    default ErrorBuilder format(Object value) {
        return new ErrorBuilder(this)
                .format(value);
    }

    default ErrorBuilder toBuilder() {
        return new ErrorBuilder(this);
    }

    default ErrorDetailBuilder toDetail() {
        return new ErrorDetailBuilder(this, Collections.emptyList());
    }
}