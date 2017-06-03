package com.aldebaran.omanager.core.model;

import com.aldebaran.data.model.Response;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CustomerResponse extends CustomerModel implements Response {

    @JsonProperty
    private String id;

    @JsonProperty
    private String imageUrl;

    @JsonProperty
    private TimestampsModel timestampsModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TimestampsModel getTimestampsModel() {
        return timestampsModel;
    }

    public void setTimestampsModel(TimestampsModel timestampsModel) {
        this.timestampsModel = timestampsModel;
    }
}