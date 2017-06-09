package com.aldebaran.chassis.hystrix;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;


public class RestCall<T> {

    private String url;
    private HttpMethod method;
    private HttpEntity<?> requestEntity;
    private Class<T> responseType;
    private Object[] uriVariables = new Object[0];

    public String getUrl() {
        return url;
    }

    public RestCall<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public RestCall<T> setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpEntity<?> getRequestEntity() {
        return requestEntity;
    }

    public RestCall<T> setRequestEntity(HttpEntity<?> requestEntity) {
        this.requestEntity = requestEntity;
        return this;
    }

    public Class<T> getResponseType() {
        return responseType;
    }

    public RestCall<T> setResponseType(Class<T> responseType) {
        this.responseType = responseType;
        return this;
    }

    public Object[] getUriVariables() {
        return uriVariables;
    }

    public RestCall<T> setUriVariables(Object[] uriVariables) {
        this.uriVariables = uriVariables;
        return this;
    }
}
