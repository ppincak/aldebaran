package com.aldebaran.rest.search;


public class OrderDescriptor {

    private final String propertyName;
    private final String resultProperty;

    public OrderDescriptor(String propertyName, String resultProperty) {
        this.propertyName = propertyName;
        this.resultProperty = resultProperty;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getResultProperty() {
        return resultProperty;
    }
}