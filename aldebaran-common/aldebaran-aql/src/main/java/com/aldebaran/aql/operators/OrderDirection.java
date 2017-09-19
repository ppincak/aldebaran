package com.aldebaran.aql.operators;

import com.aldebaran.utils.descriptor.MatchingEnum;


public enum OrderDirection implements MatchingEnum<OrderDirection> {
    ASC("asc", "ASC"),
    DESC("desc", "DESC");

    private final String[] representations;

    OrderDirection(String... representations) {
        this.representations = representations;
    }

    @Override
    public String[] getRepresentations() {
        return representations;
    }
}