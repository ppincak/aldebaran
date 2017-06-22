package com.aldebaran.auth.core.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty


data class UserRegistrationRequest(

    @get:NotEmpty
    @JsonProperty
    val username: String?,

    @get:NotEmpty
    @JsonProperty
    val email: String?,

    @get:NotEmpty
    @JsonProperty
    val password: String?,

    @get:NotEmpty
    @JsonProperty
    val repeatPassword: String?
)