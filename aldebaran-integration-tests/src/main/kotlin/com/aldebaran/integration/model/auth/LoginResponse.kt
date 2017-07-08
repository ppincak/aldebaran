package com.aldebaran.integration.model.auth

import com.fasterxml.jackson.annotation.JsonProperty


data class LoginResponse(

    @JsonProperty
    val accessToken: String?,

    @JsonProperty
    val tokenType: String?
)