package com.aldebaran.rest.error.codes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ErrorDetailBuilder extends ErrorBuilder {

    private Map<String, Object> messages;

    public ErrorDetailBuilder(ErrorEvent error, List<Object> replacements) {
        super(error);
        replacements.addAll(replacements);
    }

    public Map<String, Object> getMessages() {
        return messages;
    }

    public ErrorDetailBuilder add(String key, Object value) {
        if(messages == null) {
            messages = new HashMap<>();
        }
        messages.put(key, value);
        return this;
    }
}