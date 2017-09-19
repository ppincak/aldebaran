package com.aldebaran.utils.operators;

import com.aldebaran.utils.descriptor.MatchingEnum;


public enum ComparisonOperator implements MatchingEnum<ComparisonOperator> {
    EQUALS("="),
    NOT_EQUALS("!="),
    LESS_THAN("<"),
    GREATER_THAN(">"),
    LESS_THAN_EQUALS("<="),
    GREATER_THAN_EQUALS(">="),
    IN("in", "IN"),
    LIKE("like", "LIKE"),
    ILIKE("ilike", "ILIKE");

    private final String[] representations;

    ComparisonOperator(String... representations) {
        this.representations = representations;
    }

    @Override
    public String[] getRepresentations() {
        return representations;
    }
}

