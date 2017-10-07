package com.aldebaran.order.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;


@JsonPropertyOrder({"id", "name", "description", "price"})
public class ProductResponse extends ProductModel {

    @JsonProperty
    private Long id;

    @JsonProperty
    private TimestampsModel timestamps;

    @JsonProperty
    private List<ImageModel> images = new ArrayList<>();

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

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }
}