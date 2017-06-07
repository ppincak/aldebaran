package com.aldebaran.auth.core.jersey

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider


@Provider
open class ObjectMapperProvider : ContextResolver<ObjectMapper> {

    var objectMapper: ObjectMapper? = null

    constructor() : super() {
        objectMapper =
                ObjectMapper()
                    .registerModule(KotlinModule())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
    }

    override fun getContext(type: Class<*>?): ObjectMapper? {
        return objectMapper;
    }
}