package com.aldebaran.integration.test

import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification


abstract class AbstractAuthServiceRoot : AbstractRoot() {

    override fun baseUrl(): String {
        return config.getString("service.auth.baseUrl")
    }

    override fun requestSpecification(): RequestSpecification {
        return RequestSpecBuilder().build()
    }

    override fun responseSpecification(): ResponseSpecification {
        return ResponseSpecBuilder().build()
    }
}