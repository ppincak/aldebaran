package com.aldebaran.order.api.jersey;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.hateoas.core.AnnotationRelProvider;
import org.springframework.hateoas.hal.Jackson2HalModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
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
                    .registerModule(new Jackson2HalModule())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .configure(SerializationFeature.INDENT_OUTPUT, true)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper
                .setHandlerInstantiator(new Jackson2HalModule.HalHandlerInstantiator(
                                                new AnnotationRelProvider(), null, null));
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}