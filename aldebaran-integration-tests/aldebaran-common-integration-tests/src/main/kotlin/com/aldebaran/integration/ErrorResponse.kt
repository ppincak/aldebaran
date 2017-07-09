package com.aldebaran.integration

import com.fasterxml.jackson.annotation.JsonProperty


data class  ErrorResponse(

    @JsonProperty
    val status: Int,

    @JsonProperty
    val code: Int,

    @JsonProperty
    val subCode: Int,

    @JsonProperty
    val key: String,

    @JsonProperty
    val message: String
)
