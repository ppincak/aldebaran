package com.aldebaran.integration.order.model

import com.fasterxml.jackson.annotation.JsonProperty


open class CustomerModel(@JsonProperty val firstName: String,
                         @JsonProperty val lastName: String,
                         @JsonProperty val phone: String,
                         @JsonProperty val email: String) {

}