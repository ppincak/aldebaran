package com.aldebaran.rest.handlers;

import com.aldebaran.rest.error.codes.ApplicationException;
import com.aldebaran.rest.error.codes.ErrorBuilder;
import com.aldebaran.rest.error.codes.ErrorDetailBuilder;
import com.aldebaran.rest.error.codes.ErrorResponse;
import com.aldebaran.rest.error.GeneralErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Exception> {

    @Autowired
    private ErrorResponseCreator responseCreator;

    @Override
    public Response toResponse(Exception exception) {
        if(exception instanceof ApplicationException) {
            return handleApplicationException((ApplicationException) exception);
        }
        return handleGeneralException(exception);
    }

    private Response handleGeneralException(Exception exception) {
        // TODO log

        ErrorResponse response =
                responseCreator.getErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                                 GeneralErrorCodes.INTERNAL_SERVER_ERROR);

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