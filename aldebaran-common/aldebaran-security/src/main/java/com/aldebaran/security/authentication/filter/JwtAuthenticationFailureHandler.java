package com.aldebaran.security.authentication.filter;

import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.codes.ErrorResponse;
import com.aldebaran.rest.handlers.ErrorResponseCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ErrorResponseCreator responseCreator;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        int statusCode = Response.Status.UNAUTHORIZED.getStatusCode();

        ErrorResponse errorResponse =
                responseCreator.getErrorResponse(statusCode, GeneralErrorEvents.UNAUTHORIZED);

        response.addHeader("Content-Type", "application/json");
        response.setStatus(statusCode);
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
