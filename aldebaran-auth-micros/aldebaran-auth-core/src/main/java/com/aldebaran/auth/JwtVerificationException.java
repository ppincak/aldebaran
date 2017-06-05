package com.aldebaran.auth;


public class JwtVerificationException extends RuntimeException {

    public JwtVerificationException() {
    }

    public JwtVerificationException(String message) {
        super(message);
    }
}
