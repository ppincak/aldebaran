package com.aldebaran.auth.core.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull


data class TokenRevokeRequest(

    @NotNull
    @JsonProperty
    val jtis: List<String>)