package com.aldebaran.integration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory
import io.restassured.parsing.Parser
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification


abstract class AbstractRoot {

    init {
        loadProperties()
        configureObjectMapper()
        configureRestAssured()
    }

    protected lateinit var objectMapper: ObjectMapper;
    protected lateinit var config: Config;

    protected abstract fun baseUrl() : String
    protected abstract fun requestSpecification() : RequestSpecification;
    protected abstract fun responseSpecification() : ResponseSpecification;

    protected fun loadProperties() : Unit {
        config = ConfigFactory.load();
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

    private fun configureObjectMapper() {
        objectMapper =
                ObjectMapper()
                        .registerModule(KotlinModule())
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    }

    private fun objectMapperConfig() : ObjectMapperConfig {
        val objectMapperFactory = Jackson2ObjectMapperFactory { cls, charset ->
            objectMapper
        }

        return ObjectMapperConfig
                    .objectMapperConfig()
                    .jackson2ObjectMapperFactory(objectMapperFactory);
    }
}