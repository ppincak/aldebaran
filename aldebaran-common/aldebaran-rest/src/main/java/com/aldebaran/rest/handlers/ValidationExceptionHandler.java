package com.aldebaran.rest.handlers;

import com.aldebaran.rest.error.ValidationErrorsMap;
import com.aldebaran.rest.error.codes.ErrorEvent;
import com.aldebaran.rest.error.codes.ErrorResponse;
import com.aldebaran.rest.error.ValidationErrorCodes;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private ErrorResponseCreator responseCreator;

    public ValidationExceptionHandler(ErrorResponseCreator responseCreator) {
        this.responseCreator = responseCreator;
    }

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<Map<String, Object>> errorDetails = new HashSet<>();

        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for(ConstraintViolation<?> violation:  violations) {
            String fieldName = getFieldName(violation.getPropertyPath().toString());
            Object fieldValue = violation.getInvalidValue();
            Object[] replacements = new Object[]{fieldName, fieldValue};
            Map<String, Object> detail =
                    responseCreator.getErrorDetail(getValidationError(violation.getConstraintDescriptor()),
                                                                      replacements);
            detail.put("fieldName", fieldName);
            detail.put("invalidValue", fieldValue);
            errorDetails.add(detail);
        }

        ErrorResponse errorResponse =
                responseCreator.getErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(),
                                                 ValidationErrorCodes.VALIDATION_ERROR);

        errorResponse.setErrorDetails(errorDetails);
        errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }

    private ErrorEvent getValidationError(ConstraintDescriptor constraintDescriptor) {
        return ValidationErrorsMap
                    .resolveErrorEvent(constraintDescriptor.getAnnotation().annotationType());
    }

    private String getFieldName(String propertyPath) {
        String[] splitPath = propertyPath.split("\\.");
        if(splitPath.length == 0) {
            return "";
        }
        return splitPath[splitPath.length-1];
    }
}