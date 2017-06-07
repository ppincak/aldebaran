package com.aldebaran.auth.core.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


data class TokenResponse (

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val accessToken: String?,

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val refreshToken: String?,

    @JsonProperty
    val tokenType: String,

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val scope: Set<String>?,

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val expiresIn: Long?
)