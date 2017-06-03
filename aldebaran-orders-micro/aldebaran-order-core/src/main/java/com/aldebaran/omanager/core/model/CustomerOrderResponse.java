package com.aldebaran.omanager.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class CustomerOrderResponse {

    @JsonProperty
    private Long orderId;

    @JsonProperty("sum")
    private PriceModel priceModel;

    @JsonProperty
    private List<CustomerOrderProductModel> products;

    @JsonProperty
    private TimestampsModel timestamps;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public PriceModel getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(PriceModel priceModel) {
        this.priceModel = priceModel;
    }

    public List<CustomerOrderProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<CustomerOrderProductModel> products) {
        this.products = products;
    }

    public TimestampsModel getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(TimestampsModel timestamps) {
        this.timestamps = timestamps;
    }
}