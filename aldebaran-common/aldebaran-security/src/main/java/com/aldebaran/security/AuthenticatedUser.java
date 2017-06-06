package com.aldebaran.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public interface AuthenticatedUser extends UserDetails {

    Long getUserId();
    String getTempId();
    String getUsername();
    String getEmail();
    String getClientId();
    Long getExpiresAt();
    Collection<String> getScopes();
}
