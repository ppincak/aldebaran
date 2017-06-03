package com.aldebaran.omanager.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductResponse extends ProductModel {

    @JsonProperty
    private Long id;

    @JsonProperty
    private TimestampsModel timestamps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimestampsModel getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(TimestampsModel timestamps) {
        this.timestamps = timestamps;
    }
}