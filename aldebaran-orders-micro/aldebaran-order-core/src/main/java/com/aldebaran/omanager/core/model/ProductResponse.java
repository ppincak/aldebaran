package com.aldebaran.omanager.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ProductResponse extends ProductModel {

    @JsonProperty
    private Long id;

    @JsonProperty
    private TimestampsModel timestamps;

    @JsonProperty
    private List<String> images;

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}