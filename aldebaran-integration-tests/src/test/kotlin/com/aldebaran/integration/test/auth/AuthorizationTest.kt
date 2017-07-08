package com.aldebaran.integration.test.auth

import com.aldebaran.integration.model.auth.AuthorizationResponse
import com.aldebaran.integration.model.ErrorResponse
import com.aldebaran.integration.test.AbstractAuthServiceRoot
import com.aldebaran.integration.test.getAccessToken
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