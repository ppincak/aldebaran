package com.aldebaran.rest.interceptors;


public class ApiKeyException extends RuntimeException {

    public ApiKeyException() {
        super("Invalid api key");
    }
}