package com.aldebaran.auth.core.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class UsernamePasswordAuthentication implements Authentication {

    private final String username;
    private final String password;

    private boolean authenticated;
    private Object principal;
    private UserDetails details;

    public UsernamePasswordAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return details.getAuthorities();
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }
}