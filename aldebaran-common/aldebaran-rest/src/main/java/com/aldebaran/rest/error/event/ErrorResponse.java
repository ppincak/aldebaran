package com.aldebaran.rest.error.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;


public class ErrorResponse {

    @JsonProperty
    private Integer status;

    @JsonProperty
    private Integer code;

    @JsonProperty
    private Integer subcode;

    @JsonProperty
    private String key;

    @JsonProperty
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String devMessage;

    @JsonProperty
    private Collection<Map<String, Object>> errorDetails = new HashSet<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getSubcode() {
        return subcode;
    }

    public void setSubcode(Integer subcode) {
        this.subcode = subcode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public Collection<Map<String, Object>> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Collection<Map<String, Object>> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
