package com.aldebaran.security.jwt;

import com.aldebaran.security.authentication.AuthenticatedUser;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class TokenInfo {

    @JsonProperty
    private Long userId;

    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String clientId;

    @JsonProperty
    private Long expiresAt;

    @JsonProperty
    private List<String> roles;

    public TokenInfo(AuthenticatedUser authenticatedUser) {
        userId = authenticatedUser.getUserId();
        username = authenticatedUser.getUsername();
        email = authenticatedUser.getEmail();
        clientId = authenticatedUser.getClientId();
        expiresAt = authenticatedUser.getExpiresAt();
        roles = TokenUtils.extractRoles(authenticatedUser.getAuthorities());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}