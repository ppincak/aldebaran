package com.aldebaran.api.jersey;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.ws.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Provider
public class JerseyObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    public JerseyObjectMapperProvider() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule
            .addDeserializer(LocalDateTime.class,
                             new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));

        objectMapper =
                new ObjectMapper()
                    .registerModule(javaTimeModule)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}