package com.aldebaran.auth.model

import com.fasterxml.jackson.annotation.JsonProperty


data class TokenRequest(

    @JsonProperty
    val username: String,

    @JsonProperty
    val password: String,
    
    @JsonProperty("client_id")
    val clientId: String,
    
    @JsonProperty("client_secret")
     val clientSecret: String,
    
    @JsonProperty("grant_type")
    val grantType: String,
    
    @JsonProperty("scope")
    val scope: String,
    
    @JsonProperty("state")
    val state: String,
    
    @JsonProperty("refresh_token")
    val refreshToken: String
)