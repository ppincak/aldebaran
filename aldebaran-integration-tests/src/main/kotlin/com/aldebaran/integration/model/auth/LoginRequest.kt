package com.aldebaran.integration.model.auth

import com.fasterxml.jackson.annotation.JsonProperty


data class LoginRequest(

    @JsonProperty
    val username: String?,

    @JsonProperty
    val password: String?,

    @JsonProperty("grant_type")
    val grantType: String?
)