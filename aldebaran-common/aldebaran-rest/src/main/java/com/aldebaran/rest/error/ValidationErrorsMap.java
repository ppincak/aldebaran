package com.aldebaran.rest.error;

import com.aldebaran.rest.error.event.ErrorEvent;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;


public class ValidationErrorsMap {

    private final static Map<Class<? extends Annotation>, ErrorEvent> validationMap;

    static {
        validationMap = new HashMap<>();
        validationMap.put(NotNull.class, ValidationErrorEvents.NOT_NULL_ERROR);
        validationMap.put(NotEmpty.class, ValidationErrorEvents.NOT_EMPTY_ERROR);
        validationMap.put(Email.class, ValidationErrorEvents.INVALID_EMAIL_ADDRESS);
    }

    public static ErrorEvent resolveErrorEvent(Class<? extends Annotation> annotation) {
        return validationMap.get(annotation);
    }
}