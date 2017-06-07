package com.aldebaran.security;

import org.springframework.security.core.GrantedAuthority;


public class SimpleGrantedAuthority implements GrantedAuthority {

    private String authority;

    public SimpleGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}