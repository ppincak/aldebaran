package com.aldebaran.rest.error.codes;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class ApplicationException extends RuntimeException {

    private final ErrorBuilder errorBuilder;
    private final Set<ErrorBuilder> detail = new HashSet<>();

    private int status = 0;

    public ApplicationException(ErrorEvent errorBuilder) {
        this.errorBuilder = new ErrorBuilder(errorBuilder);
    }

    public ApplicationException(ErrorBuilder errorBuilder) {
        this.errorBuilder = errorBuilder;
    }

    public ErrorBuilder getErrorBuilder() {
        return errorBuilder;
    }

    public Set<ErrorBuilder> getDetail() {
        return detail;
    }

    public int getStatus() {
        return status;
    }

    public ApplicationException status(int status) {
        this.status = status;
        return this;
    }

    public ApplicationException addDetail(ErrorBuilder error) {
        detail.add(error);
        return this;
    }

    public ApplicationException addDetail(ErrorEvent error) {
        this.detail.add(new ErrorBuilder(error));
        return this;
    }

    public ApplicationException addDetail(Collection<ErrorBuilder> errors) {
        this.detail.addAll(errors);
        return this;
    }
}