package com.aldebaran.integration.test.auth

import com.aldebaran.integration.ErrorResponse
import com.aldebaran.integration.model.LoginRequest
import com.aldebaran.integration.model.LoginResponse
import com.aldebaran.integration.test.AbstractAuthServiceRoot
import io.restassured.RestAssured
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test


open class LoginTest : AbstractAuthServiceRoot() {

    @Test(description = "Test of login functionality")
    fun testLogin() : Unit {
        val loginRequest =
                LoginRequest(credentials.username,
                        credentials.password,
                        "password")

        val loginResponse =
            baseLoginRequest(loginRequest, 200)
                .`as`(LoginResponse::class.java)

        Assert.assertNotNull(loginResponse.accessToken)
        Assert.assertNotNull(loginResponse.tokenType)
    }

    @Test(description = "Test login with unsupported grant type")
    fun testLoginUnsupportedGrantType() : Unit {
        val loginRequest =
                LoginRequest("", "", "unsupported")

        val errorResponse =
            baseLoginRequest(loginRequest, 400)
                .`as`(ErrorResponse::class.java)

        Assert.assertEquals(errorResponse.key, "unsupported.grant.type")
    }

    @Test(description = "Test login with invalid credentials")
    fun testLoginBadCredentials() : Unit {
        val loginRequest =
                LoginRequest("", "", "password")

        val errorResponse =
                baseLoginRequest(loginRequest, 400)
                    .`as`(ErrorResponse::class.java)

        Assert.assertEquals(errorResponse.key, "bad.credentials")
    }

    private fun baseLoginRequest(loginRequest: LoginRequest, statusCode: Int) : Response {
        return RestAssured
                .expect()
                    .statusCode(statusCode)
                .log()
                    .all()
                .given()
                    .contentType("application/json")
                    .body(loginRequest)
                .post("/oauth2/token")
    }
}