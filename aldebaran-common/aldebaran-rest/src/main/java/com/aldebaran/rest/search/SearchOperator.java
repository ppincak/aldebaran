package com.aldebaran.rest.search;

import com.aldebaran.utils.descriptor.LabelEnum;
import com.aldebaran.utils.descriptor.MatchingEnum;


public enum SearchOperator implements LabelEnum, MatchingEnum {
    EQUALS(":eq:", "="),
    NOT_EQUALS(":neq:", "!="),
    LESS_THAN(":lt:", "<"),
    GREATER_THAN(":gt:", ">"),
    LESS_THAN_EQUALS(":lte:", "<="),
    GREATER_THAN_EQUALS(":gte:", ">="),
    IN(":in:", "in", "IN"),
    LIKE(":li:", "like", "LIKE"),
    ILIKE(":ili:", "ILIKE", "ILIKE");

    private final String label;
    private final String[] representations;

    SearchOperator(String label, String... representations) {
        this.label = label;
        this.representations = representations;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String[] getRepresentations() {
        return representations;
    }
}
