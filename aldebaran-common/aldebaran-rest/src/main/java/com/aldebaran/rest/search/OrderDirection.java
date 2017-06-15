package com.aldebaran.rest.search;

import com.aldebaran.utils.descriptor.LabelEnum;


public enum OrderDirection implements LabelEnum {
    ASCENDING("asc"),
    ASC("desc");

    private final String label;

    OrderDirection(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}