package com.aldebaran.integration.model

import com.fasterxml.jackson.annotation.JsonProperty


data class AuthorizationResponse(

    @JsonProperty
    val userId: Long?,

    @JsonProperty
    val username: String?,

    @JsonProperty
    val email: String?,

    @JsonProperty
    val clientId: String?,

    @JsonProperty
    val expiresAt: Long?
)