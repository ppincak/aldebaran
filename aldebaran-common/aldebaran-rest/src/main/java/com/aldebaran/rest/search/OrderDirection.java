package com.aldebaran.rest.search;

import com.aldebaran.utils.descriptors.Labelable;


public enum OrderDirection implements Labelable {
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