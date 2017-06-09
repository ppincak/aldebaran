package com.aldebaran.server.authentication;

import org.springframework.security.core.AuthenticationException;


public class UnauthorizedException extends AuthenticationException  {

    public UnauthorizedException() {
        super("Unauthorized");
    }
}
