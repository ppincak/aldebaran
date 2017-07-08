package com.aldebaran.integration.model.order

import com.fasterxml.jackson.annotation.JsonProperty

data class CustomerRequest(

    @JsonProperty
    var firstName: String?,

    @JsonProperty
    var lastName: String?,

    @JsonProperty
    var phone: String?,

    @JsonProperty
    var email: String?
)