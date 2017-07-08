package com.aldebaran.integration.test.order

import com.aldebaran.integration.model.order.CustomerRequest
import com.aldebaran.integration.test.AbstractOrderServiceRoot
import com.aldebaran.integration.test.addAuthorization
import io.restassured.RestAssured
import org.testng.annotations.Test


open class CustomerTest : AbstractOrderServiceRoot() {

    init {

    }

    private lateinit var accessToken: String

    @Test(description = "Test customer creation functionality")
    fun testCustomerCreation() {
        val customerRequest =
                CustomerRequest("firstName", "lastName", "00", "first.last@mail.com");

        addAuthorization(
            RestAssured
                .expect()
                    .statusCode(200)
                .request(), credentials)
            .log()
                .all()
            .given()
                .contentType("application/json")
                .body(customerRequest)
            .post("/customers")
    }
}