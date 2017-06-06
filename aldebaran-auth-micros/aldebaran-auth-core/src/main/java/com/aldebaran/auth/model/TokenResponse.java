package com.aldebaran.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;


public class TokenResponse {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String refreshToken;

    @JsonProperty
    private String tokenType;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<String> scope;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}