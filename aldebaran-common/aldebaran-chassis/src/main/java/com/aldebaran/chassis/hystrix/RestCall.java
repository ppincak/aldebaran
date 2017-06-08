package com.aldebaran.chassis.hystrix;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;


public class RestCall<T> {

    private String url;
    private HttpMethod method;
    private HttpEntity<?> requestEntity;
    private Class<T> responseType;
    private Object[] uriVariables;

    public String getUrl() {
        return url;
    }

    public RestCall setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public RestCall setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpEntity<?> getRequestEntity() {
        return requestEntity;
    }

    public RestCall setRequestEntity(HttpEntity<?> requestEntity) {
        this.requestEntity = requestEntity;
        return this;
    }

    public Class<T> getResponseType() {
        return responseType;
    }

    public RestCall setResponseType(Class<T> responseType) {
        this.responseType = responseType;
        return this;
    }

    public Object[] getUriVariables() {
        return uriVariables;
    }

    public RestCall setUriVariables(Object[] uriVariables) {
        this.uriVariables = uriVariables;
        return this;
    }
}
