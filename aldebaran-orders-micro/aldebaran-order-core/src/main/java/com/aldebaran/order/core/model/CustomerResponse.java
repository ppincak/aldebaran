package com.aldebaran.order.core.model;

import com.aldebaran.data.model.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({ "id", "firstName", "lastName", "email", "phone" })
public class CustomerResponse extends CustomerModel implements Response {

    @JsonProperty
    private Long customerId;

    @JsonProperty
    private String imageUrl;

    @JsonProperty
    private TimestampsModel timestamps;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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