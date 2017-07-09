package com.aldebaran.integration.auth

import com.fasterxml.jackson.annotation.JsonProperty


data class TokenRevokeRequest(

    @JsonProperty
    val jtis: List<String>
)