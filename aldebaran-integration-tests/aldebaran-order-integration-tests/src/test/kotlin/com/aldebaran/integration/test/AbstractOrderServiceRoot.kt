package com.aldebaran.integration.test

import com.aldebaran.integration.API_KEY_HEADER
import com.aldebaran.integration.API_LANG_HEADER
import com.aldebaran.integration.AbstractRoot
import com.aldebaran.integration.Credentials
import com.aldebaran.integration.model.LoginRequest
import com.aldebaran.integration.model.LoginResponse
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


abstract class AbstractOrderServiceRoot : AbstractRoot() {

    init {
        iniOkHttp()
        iniProperties();
        iniAuthentication(defaultCredentials())
    }

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var authenticationUrl: String

    protected lateinit var accessToken: String
    protected lateinit var authorizationHeader: String

    private fun iniOkHttp() {
        okHttpClient = OkHttpClient()
    }

    private fun iniProperties() {
        authenticationUrl = config.getString("service.authentication.baseUrl");
    }

    private fun iniAuthentication(credentials: Credentials) {
        accessToken = getAccessToken(credentials)!!
        authorizationHeader = "Bearer " + accessToken
    }

    protected fun defaultCredentials() : Credentials {
        return Credentials(config.getString("service.authentication.credentials.username"),
                           config.getString("service.authentication.credentials.password"))
    }

    protected fun getAccessToken(credentials: Credentials) : String? {
        val loginRequest = LoginRequest(credentials.username,
                                        credentials.password,
                                        "password");
        val requestBody =
                RequestBody
                    .create(MediaType.parse("application/json"),
                            objectMapper.writeValueAsBytes(loginRequest))

        val request =
                Request
                    .Builder()
                    .url(authenticationUrl)
                    .post(requestBody)
                    .build()

        val response = okHttpClient.newCall(request).execute()
        val loginResponse =
                objectMapper
                    .readValue<LoginResponse>(response.body()?.bytes(), LoginResponse::class.java)

        return loginResponse?.accessToken
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