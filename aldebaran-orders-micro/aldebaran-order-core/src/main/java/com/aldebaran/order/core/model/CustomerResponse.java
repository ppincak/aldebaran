package com.aldebaran.order.core.model;

import com.aldebaran.data.model.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({ "id", "firstName", "lastName", "email", "phone" })
public class CustomerResponse extends CustomerModel implements Response {

    @JsonProperty
    private String id;

    @JsonProperty
    private String imageUrl;

    @JsonProperty
    private TimestampsModel timestamps;

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

    public TimestampsModel getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(TimestampsModel timestamps) {
        this.timestamps = timestamps;
    }
}