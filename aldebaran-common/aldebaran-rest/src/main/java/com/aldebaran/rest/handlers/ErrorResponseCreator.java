package com.aldebaran.rest.handlers;

import com.aldebaran.rest.error.codes.ErrorEvent;
import com.aldebaran.rest.error.codes.ErrorResponse;
import com.aldebaran.data.message.MessageProvider;
import com.aldebaran.rest.error.ValidationErrorCodes;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


public class ErrorResponseCreator {

    private final MessageProvider messageProvider;

    public ErrorResponseCreator(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    public ErrorResponse getErrorResponse(Integer status, ErrorEvent error, Object... replacements) {
        String localizedMessage =
                messageProvider.getMessage(error.getErrorMessageKey(),
                                           replacements,
                                           new Locale("en"));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(error.getErrorCode());
        errorResponse.setSubcode(error.getErrorSubCode());
        errorResponse.setKey(error.getErrorMessageKey());
        errorResponse.setMessage(localizedMessage);
        errorResponse.setStatus(status);
        return errorResponse;
    }

    public Map<String, Object> getErrorDetail(ErrorEvent error, Object... replacements) {
        String localizedMessage =
                messageProvider.getMessage(error.getErrorMessageKey(),
                                           replacements,
                                           new Locale("en"));

        Map<String, Object> detail = new LinkedHashMap<>();
        detail.put("code", error.getErrorCode());
        detail.put("subCode", error.getErrorSubCode());
        detail.put("key", error.getErrorMessageKey());
        detail.put("message", localizedMessage);
        return detail;
    }
}