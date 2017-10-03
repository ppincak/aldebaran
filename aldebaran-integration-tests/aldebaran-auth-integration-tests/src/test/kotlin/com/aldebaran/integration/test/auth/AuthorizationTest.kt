package com.aldebaran.integration.test.auth

import com.aldebaran.integration.Credentials
import com.aldebaran.integration.auth.AuthorizationResponse
import com.aldebaran.integration.model.ErrorResponse
import com.aldebaran.integration.auth.TokenRevokeRequest
import com.aldebaran.integration.model.LoginRequest
import com.aldebaran.integration.model.LoginResponse
import com.aldebaran.integration.test.AbstractAuthServiceRoot
import io.restassured.RestAssured
import io.restassured.response.Response
import org.testng.Assert
import org.testng.annotations.Test


open class AuthorizationTest : AbstractAuthServiceRoot() {

    @Test(description = "Test successful authorization")
    fun testSuccessfulAuthorization() : Unit {
        val accessToken = getAccessToken(credentials)

        val authorizationResponse =
                authorizationRequest(accessToken, 200)
                    .`as`(AuthorizationResponse::class.java)

        Assert.assertNotNull(authorizationResponse)
        Assert.assertEquals(authorizationResponse.username, "ppincak")
    }

    @Test(description = "Test unsuccessful authorization")
    fun testUnsuccessfulAuthorization() : Unit {
        val errorResponse =
                authorizationRequest("invalidAccessToken", 400)
                    .`as`(ErrorResponse::class.java)

        Assert.assertNotNull(errorResponse)
        Assert.assertEquals(errorResponse.key, "unauthorized.access")
    }

    @Test(description = "Test unsuccessful authorization")
    fun testRevokedToken() : Unit {
        val accessToken = getAccessToken(credentials)
        val revokeRequest = TokenRevokeRequest(arrayListOf(accessToken!!))

        RestAssured
            .expect()
                .statusCode(200)
            .log()
                .all()
            .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(revokeRequest)
            .post("/security/token/revoke")

        val authorizationResponse =
                authorizationRequest(accessToken, 401)
                        .`as`(AuthorizationResponse::class.java)

        Assert.assertNotNull(authorizationResponse)
    }

    private fun getAccessToken(credentials: Credentials) : String? {
        return getAccessToken(credentials.username, credentials.password)
    }

    private fun getAccessToken(username: String, password: String) : String? {
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

    private fun authorizationRequest(accessToken: String?, statusCode: Int) : Response {
        return RestAssured
            .expect()
                .statusCode(statusCode)
            .log()
                .all()
            .given()
                .header("Authorization", "Bearer " + accessToken)
            .get("/oauth2/tokenInfo")
    }
}