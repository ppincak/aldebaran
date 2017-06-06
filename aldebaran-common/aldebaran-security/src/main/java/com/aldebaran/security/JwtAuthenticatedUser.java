package com.aldebaran.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class JwtAuthenticatedUser implements AuthenticatedUser {

    private Long userId;
    private String tempId;
    private String username;
    private String email;
    private String jwt;
    private String clientId;
    private Long expiresAt;
    private Collection<? extends GrantedAuthority> authorities;
    private Collection<String> scopes;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getTempId() {
        return tempId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Long getExpiresAt() {
        return expiresAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<String> getScopes() {
        return scopes;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setScopes(Collection<String> scopes) {
        this.scopes = scopes;
    }
}