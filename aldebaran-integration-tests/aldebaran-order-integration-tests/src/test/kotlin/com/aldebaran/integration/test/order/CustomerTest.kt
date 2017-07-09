package com.aldebaran.integration.test.order

import com.aldebaran.integration.order.model.CustomerRequest
import com.aldebaran.integration.test.AbstractOrderServiceRoot
import io.restassured.RestAssured
import org.testng.annotations.Test


open class CustomerTest : AbstractOrderServiceRoot() {

    private lateinit var accessToken: String

    @Test(description = "Test customer creation functionality")
    fun testCustomerCreation() {
        val customerRequest =
                CustomerRequest("firstName", "lastName", "00", "testtt.test@mail.com");

        RestAssured
            .expect()
                .statusCode(201)
            .log()
                .all()
            .given()
                .contentType("application/json")
                .body(customerRequest)
            .post("/customers")
    }
}