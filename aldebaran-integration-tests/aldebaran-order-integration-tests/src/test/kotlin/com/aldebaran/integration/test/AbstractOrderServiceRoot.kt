package com.aldebaran.integration.test

import com.aldebaran.integration.API_KEY_HEADER
import com.aldebaran.integration.API_LANG_HEADER
import com.aldebaran.integration.AbstractRoot
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification


abstract class AbstractOrderServiceRoot : AbstractRoot() {

    private lateinit var accessToken: String

    override fun baseUrl(): String {
        return config.getString("service.baseUrl")
    }

    override fun requestSpecification(): RequestSpecification {
        return RequestSpecBuilder()
                .addHeader(API_LANG_HEADER, "en")
                .addHeader(API_KEY_HEADER, config.getString("service.apiKey"))
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbGRlYmFyYW4tYXV0aCIsImp0aSI6IjhjNDVlNjMzOThmZWI0MWExZjEwY2I2YjlkZmEwNjQ0IiwidWlkIjoxLCJhdWlkIjoiY2xpZW50SWQiLCJ1c3JuIjoicHBpbmNhayIsImVtbCI6InBldGVyLnBpbmNha0Bob3RtYWlsLmNvbSIsInNjcHMiOltdLCJybHMiOltdLCJleHAiOjE0OTk1ODQ5MzQsImlhdCI6MTQ5OTU4NDkyN30.ZE8Wj8EckW8TK2TB9QwOrzInMUhWu-QVa1aXiY2hvn_akSzgeJasvNzIj26_F64F8pDyvePKEZZqjNJkd3y7og")
                .build()
    }

    override fun responseSpecification(): ResponseSpecification {
        return ResponseSpecBuilder().build()
    }
}