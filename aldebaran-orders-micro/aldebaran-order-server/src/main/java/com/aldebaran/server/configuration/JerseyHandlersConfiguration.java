package com.aldebaran.server.configuration;

import com.aldebaran.data.message.MessageProvider;
import com.aldebaran.rest.handlers.ValidationExceptionHandler;
import com.aldebaran.rest.handlers.ErrorResponseCreator;
import com.aldebaran.rest.handlers.GeneralExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JerseyHandlersConfiguration {

    @Bean
    public MessageProvider provider(MessageSource messageSource) {
        return messageSource::getMessage;
    }

    @Bean
    public ErrorResponseCreator responseCreator(MessageProvider provider) {
        return new ErrorResponseCreator(provider);
    }

    @Bean
    public ValidationExceptionHandler validationHandler(ErrorResponseCreator responseCreator) {
        return new ValidationExceptionHandler(responseCreator);
    }

    @Bean
    public GeneralExceptionHandler generalExceptionHandler(ErrorResponseCreator responseCreator) {
        return new GeneralExceptionHandler(responseCreator);
    }
}