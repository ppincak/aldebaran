package com.aldebaran.auth.core.model

import com.fasterxml.jackson.annotation.JsonProperty


data class UserRegistrationResponse(

    @JsonProperty
    val id: Long,

    @JsonProperty
    val username: String?,

    @JsonProperty
    val email: String?
)