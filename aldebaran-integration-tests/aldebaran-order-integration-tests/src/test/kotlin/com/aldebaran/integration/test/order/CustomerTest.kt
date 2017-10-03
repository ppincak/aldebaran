package com.aldebaran.integration.test.order

import com.aldebaran.integration.model.ErrorResponse
import com.aldebaran.integration.order.model.CustomerRequest
import com.aldebaran.integration.order.model.CustomerResponse
import com.aldebaran.integration.test.AbstractOrderServiceRoot
import io.restassured.RestAssured
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test


open class CustomerTest : AbstractOrderServiceRoot() {

    @Test(description = "Test customer creation functionality")
    fun testCustomerCreation() {
        val customerResponse =
                customerCreationRequest(defaultCustomerRequest(), 201)
                    .`as`(CustomerResponse::class.java)

        Assert.assertEquals(customerResponse.firstName, "firstName")
        Assert.assertEquals(customerResponse.lastName, "lastName")
    }

    @Test(description = "Test customer creation functionality",
          dependsOnMethods = arrayOf("testCustomerCreation"))
    fun testCustomerCreationEmailError() {
        val errorResponse =
                customerCreationRequest(defaultCustomerRequest(), 400)
                    .`as`(ErrorResponse::class.java)
    }

    private fun defaultCustomerRequest() : CustomerRequest {
        return CustomerRequest("firstName",
                               "lastName",
                               "00",
                               "testtsssdt.test@mail.com");
    }

    private fun customerCreationRequest(customerRequest: CustomerRequest, statusCode: Int) : Response {
        return RestAssured
                    .expect()
                        .statusCode(statusCode)
                    .log()
                        .all()
                    .given()
                        .contentType("application/json")
                        .header("Authorization", authorizationHeader)
                        .body(customerRequest)
                    .post("/customers")
    }
}