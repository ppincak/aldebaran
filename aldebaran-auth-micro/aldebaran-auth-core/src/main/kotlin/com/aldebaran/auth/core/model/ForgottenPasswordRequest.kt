package com.aldebaran.auth.core.model

import com.fasterxml.jackson.annotation.JsonProperty


data class ForgottenPasswordRequest(

    @JsonProperty("email")
    val email: String?,

    @JsonProperty("username")
    val username: String?
)
