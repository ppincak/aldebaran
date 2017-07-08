package com.aldebaran.integration.test

import com.aldebaran.integration.model.API_KEY_HEADER
import com.aldebaran.integration.model.API_LANG_HEADER
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification


abstract class AbstractOrderServiceRoot : AbstractRoot() {

    override fun baseUrl(): String {
        return config.getString("service.order.baseUrl")
    }

    override fun requestSpecification(): RequestSpecification {
        return RequestSpecBuilder()
                .addHeader(API_LANG_HEADER, "en")
                .addHeader(API_KEY_HEADER, config.getString("service.order.apiKey"))
                .build()
    }

    override fun responseSpecification(): ResponseSpecification {
        return ResponseSpecBuilder().build()
    }
}