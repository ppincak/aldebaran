package com.aldebaran.server.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class JwtAuthentication implements Authentication {

    private final String jwt;

    private boolean authenticated = false;
    private Collection<? extends GrantedAuthority> authorities;
    private UserDetails details;
    private Object principal;

    public JwtAuthentication(String jwt) {
        this.jwt = jwt;
    }

    public JwtAuthentication(String jwt, Collection<? extends GrantedAuthority> authorities, UserDetails details) {
        this.jwt = jwt;
        this.authorities = authorities;
        this.details = details;
        this.principal = details;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
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

    @Override
    public String getName() {
        return null;
    }
}
