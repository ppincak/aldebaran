package com.aldebaran.rest.search;


public enum SearchOperator {
    EQUALS(":eq:", "="),
    NOT_EQUALS(":neq:", "!="),
    LESS_THAN(":lt:", "<"),
    GREATER_THAN(":gt:", ">"),
    LESS_THAN_EQUALS(":lte:", "<="),
    GREATER_THAN_EQUALS(":gte:", ">="),
    IN(":in:", "IN"),
    LIKE(":li:", "LIKE"),
    ILIKE(":ili:", "LIKE");

    private final String label;
    private final String expression;

    SearchOperator(String label, String expression) {
        this.label = label;
        this.expression = expression;
    }

    public static SearchOperator getByLabel(String label) {
        for(SearchOperator searchOperator: values()) {
            if(searchOperator.label.equals(label)) {
                return searchOperator;
            }
        }
        return null;
    }
}
