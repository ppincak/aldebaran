package com.aldebaran.rest.search;

import java.util.EnumSet;

public class SearchDescriptor {

    private String propertyName;
    private Class<?> resultType;
    private String resultProperty;
    private EnumSet<SearchOperator> supportedOperators;

    public SearchDescriptor(String propertyName,
                            Class<?> resultType,
                            String resultProperty,
                            EnumSet<SearchOperator> supportedOperators) {
        this.propertyName = propertyName;
        this.resultType = resultType;
        this.resultProperty = resultProperty;
        this.supportedOperators = supportedOperators;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public String getResultProperty() {
        return resultProperty;
    }

    public void setResultProperty(String resultProperty) {
        this.resultProperty = resultProperty;
    }

    public EnumSet<SearchOperator> getSupportedOperators() {
        return supportedOperators;
    }

    public void setSupportedOperators(EnumSet<SearchOperator> supportedOperators) {
        this.supportedOperators = supportedOperators;
    }
}