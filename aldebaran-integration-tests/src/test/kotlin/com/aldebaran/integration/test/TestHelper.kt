package com.aldebaran.integration.test

import com.aldebaran.integration.model.Credentials
import com.aldebaran.integration.model.ErrorResponse
import com.aldebaran.integration.model.auth.LoginRequest
import com.aldebaran.integration.model.auth.LoginResponse
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification
import org.testng.Assert


fun getAccessToken(credentials: Credentials) : String? {
    return getAccessToken(credentials.username, credentials.password)
}

fun getAccessToken(username: String, password: String) : String? {
    val loginRequest =
            LoginRequest(username, password, "password")

    val loginResponse =
            RestAssured
                .given()
                    .contentType("application/json")
                    .body(loginRequest)
                .post("/oauth2/token")
                .`as`(LoginResponse::class.java)

    return loginResponse.accessToken
}

fun addAuthorization(requestSpecification: RequestSpecification,
                     credentials: Credentials) : RequestSpecification {

    return requestSpecification
                .header("Authorization", "Bearer " + getAccessToken("ppincak", "123456"))
}

fun assertErrorResponse(errorResponse: ErrorResponse, code: Int, subCode: Int, key: String) : Unit {
    Assert.assertNotNull(errorResponse)
    Assert.assertEquals(errorResponse.code, code)
    Assert.assertEquals(errorResponse.subCode, subCode)
    Assert.assertEquals(errorResponse.key, key)
}