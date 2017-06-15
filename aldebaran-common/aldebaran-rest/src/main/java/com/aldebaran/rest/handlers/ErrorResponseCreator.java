package com.aldebaran.rest.handlers;

import com.aldebaran.rest.device.DeviceContext;
import com.aldebaran.rest.error.event.ErrorEvent;
import com.aldebaran.rest.error.event.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;


@Component
public class ErrorResponseCreator {

    private static final Logger logger = LoggerFactory.getLogger(ErrorResponseCreator.class);

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
                                            DeviceContext.getContext().getLocale());
        } catch (Exception e) {
            logger.error("Message for key: \"{}\" not found", error.getErrorMessageKey());
            return "";
        }
    }
}