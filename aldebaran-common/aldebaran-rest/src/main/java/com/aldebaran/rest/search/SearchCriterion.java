package com.aldebaran.rest.search;


public class SearchCriterion<T extends Comparable<T>> {

    private final SearchOperator operator;
    private final String propertyName;
    private final T value;

    public SearchCriterion(SearchOperator operator, String propertyName, T value) {
        this.operator = operator;
        this.propertyName = propertyName;
        this.value = value;
    }

    public SearchOperator getOperator() {
        return operator;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public T getValue() {
        return value;
    }
}
