package com.aldebaran.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ServiceProperties {

    @Value("${server.apiKey}")
    private String[] apiKeys;

    public String[] getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(String[] apiKeys) {
        this.apiKeys = apiKeys;
    }
}