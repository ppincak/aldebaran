package com.aldebaran.rest.handlers;

import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.event.ApplicationException;
import com.aldebaran.rest.error.event.ErrorBuilder;
import com.aldebaran.rest.error.event.ErrorDetailBuilder;
import com.aldebaran.rest.error.event.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @Autowired
    private ErrorResponseCreator responseCreator;

    @Override
    public Response toResponse(Exception exception) {
        if(exception instanceof ApplicationException) {
            return handleApplicationException((ApplicationException) exception);
        }
        if(exception instanceof NotFoundException) {
            return handleApplicationException(
                    new ApplicationException(GeneralErrorEvents.NOT_FOUND)
                            .status(Response.Status.NOT_FOUND.getStatusCode()));
        }
        if(exception instanceof NotAllowedException) {
            return handleApplicationException(
                    new ApplicationException(GeneralErrorEvents.NOT_ALLOWED)
                            .status(Response.Status.METHOD_NOT_ALLOWED.getStatusCode()));
        }
        return handleGeneralException(exception);
    }

    private Response handleGeneralException(Exception exception) {
        logger.error("Unhandled exception caught: ", exception);

        ErrorResponse response =
                responseCreator.getErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                                 GeneralErrorEvents.INTERNAL_SERVER_ERROR);

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(response)
                .build();
    }

    private Response handleApplicationException(ApplicationException exception) {
        ErrorBuilder errorBuilder = exception.getErrorBuilder();

        ErrorResponse errorResponse =
                responseCreator.getErrorResponse(exception.getStatus(),
                                                 errorBuilder.getError(),
                                                 errorBuilder.getReplacements());

        if(exception.getDetail().isEmpty() == false) {
            List<Map<String, Object>> errorDetails = new ArrayList<>();
            for(ErrorBuilder detail: exception.getDetail()) {
                Map<String, Object> detailMap =
                        responseCreator.getErrorDetail(detail.getError(),
                                                       detail.getReplacements());

                if(detail instanceof ErrorDetailBuilder) {
                    ErrorDetailBuilder detailBuilder = (ErrorDetailBuilder) detail;
                    if(detailBuilder.getMessages() != null) {
                        detailMap.putAll(detailBuilder.getMessages());
                    }
                }
            }
            errorResponse.setErrorDetails(errorDetails);
        }

        errorResponse.setStatus(exception.getStatus());

        int status = exception.getStatus() != 0 ? exception.getStatus() :
                                                  Response.Status.BAD_REQUEST.getStatusCode();

        return Response
                .status(status)
                .entity(errorResponse)
                .build();
    }
}