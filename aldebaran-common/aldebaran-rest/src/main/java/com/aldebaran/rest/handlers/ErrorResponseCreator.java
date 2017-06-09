package com.aldebaran.rest.handlers;

import com.aldebaran.rest.error.codes.ErrorEvent;
import com.aldebaran.rest.error.codes.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class ErrorResponseCreator {

    @Autowired
    private MessageSource messageSource;

    public ErrorResponse getErrorResponse(Integer status, ErrorEvent error, Object... replacements) {
        String localizedMessage = getLocalizedMessage(error, replacements);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.getErrorCode());
        errorResponse.setSubcode(error.getErrorSubCode());
        errorResponse.setKey(error.getErrorMessageKey());
        errorResponse.setMessage(localizedMessage);
        errorResponse.setStatus(status);
        return errorResponse;
    }

    public Map<String, Object> getErrorDetail(ErrorEvent error, Object... replacements) {
        String localizedMessage = getLocalizedMessage(error, replacements);

        Map<String, Object> detail = new LinkedHashMap<>();
        detail.put("code", error.getErrorCode());
        detail.put("subCode", error.getErrorSubCode());
        detail.put("key", error.getErrorMessageKey());
        detail.put("message", localizedMessage);
        return detail;
    }

    private String getLocalizedMessage(ErrorEvent error, Object... replacements) {
        try {
            return messageSource.getMessage(error.getErrorMessageKey(),
                                            replacements,
                                            new Locale("en"));
        } catch (Exception e) {
            return "";
        }
    }
}