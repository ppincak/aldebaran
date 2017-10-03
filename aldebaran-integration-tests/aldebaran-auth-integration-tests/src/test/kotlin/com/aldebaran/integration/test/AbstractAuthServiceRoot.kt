package com.aldebaran.integration.test

import com.aldebaran.integration.API_KEY_HEADER
import com.aldebaran.integration.API_LANG_HEADER
import com.aldebaran.integration.AbstractRoot
import com.aldebaran.integration.Credentials
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification


abstract class AbstractAuthServiceRoot : AbstractRoot() {

    protected lateinit var credentials: Credentials;

    protected fun loadCredentials() : Unit {
        credentials = Credentials(config.getString("service.credentials.username"),
                                  config.getString("service.credentials.password"))

    }

    override fun baseUrl(): String {
        return config.getString("service.baseUrl")
    }

    override fun requestSpecification(): RequestSpecification {
        return RequestSpecBuilder()
                .addHeader(API_LANG_HEADER, "en")
                .addHeader(API_KEY_HEADER, config.getString("service.apiKey"))
                .build()
    }

    override fun responseSpecification(): ResponseSpecification {
        return ResponseSpecBuilder().build()
    }
}