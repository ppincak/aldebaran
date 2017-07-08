package com.aldebaran.integration.test

import com.aldebaran.integration.model.Credentials
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.mapper.factory.DefaultJackson2ObjectMapperFactory
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory
import io.restassured.parsing.Parser
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification


abstract class AbstractRoot {

    init {
        loadProperties()
        loadCredentials()
        configureRestAssured()
    }

    protected lateinit var config: Config;
    protected lateinit var credentials: Credentials;

    protected abstract fun baseUrl() : String
    protected abstract fun requestSpecification() : RequestSpecification;
    protected abstract fun responseSpecification() : ResponseSpecification;

    protected fun loadProperties() : Unit {
        config = ConfigFactory.load();
    }

    protected fun loadCredentials() : Unit {
        credentials = Credentials(config.getString("service.credentials.username"),
                                  config.getString("service.credentials.password"))

    }

    private fun configureRestAssured() : Unit {
        RestAssured.baseURI = baseUrl()
        RestAssured.defaultParser = Parser.JSON
        RestAssured.requestSpecification = requestSpecification()
        RestAssured.responseSpecification = responseSpecification()

        RestAssured.config =
                RestAssuredConfig
                        .config()
                        .objectMapperConfig(objectMapperConfig())
    }

    private fun objectMapperConfig() : ObjectMapperConfig {
        val objectMapperFactory = Jackson2ObjectMapperFactory { cls, charset ->
            val objectMapper =
                    ObjectMapper()
                            .registerModule(KotlinModule())
                            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)

            objectMapper
        }

        return ObjectMapperConfig
                    .objectMapperConfig()
                    .jackson2ObjectMapperFactory(objectMapperFactory);
    }
}