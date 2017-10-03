package com.aldebaran.aql.errors;


public class InvalidQuerySyntaxException extends RuntimeException {

    public InvalidQuerySyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
}