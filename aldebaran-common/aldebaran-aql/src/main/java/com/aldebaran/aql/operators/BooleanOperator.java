package com.aldebaran.aql.operators;

import com.aldebaran.utils.descriptor.MatchingEnum;


public enum BooleanOperator implements MatchingEnum<BooleanOperator >{
    AND("and", "AND"),
    OR("or", "OR");

    private String[] representations;

    BooleanOperator(String... representations) {
        this.representations = representations;
    }

    @Override
    public String[] getRepresentations() {
        return representations;
    }
}
