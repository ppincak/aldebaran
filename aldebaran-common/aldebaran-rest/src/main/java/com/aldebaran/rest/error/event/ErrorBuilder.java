package com.aldebaran.rest.error.event;

import java.util.ArrayList;
import java.util.List;


public class ErrorBuilder {

    protected final ErrorEvent error;
    protected final List<Object> replacements = new ArrayList<>();

    public ErrorBuilder(ErrorEvent error) {
        this.error = error;
    }

    public ErrorEvent getError() {
        return error;
    }

    public List<Object> getReplacements() {
        return replacements;
    }

    public ErrorBuilder format(Object value) {
        this.replacements.add(value);
        return this;
    }

    public ErrorDetailBuilder toDetail() {
        return new ErrorDetailBuilder(error, replacements);
    }
}