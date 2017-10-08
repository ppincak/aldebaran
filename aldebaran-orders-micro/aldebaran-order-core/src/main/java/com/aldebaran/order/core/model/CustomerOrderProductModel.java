package com.aldebaran.order.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CustomerOrderProductModel {

    @JsonProperty
    private Long productId;

    @JsonProperty
    private String name;

    @JsonProperty
    private PriceModel price;

    @JsonProperty
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriceModel getPrice() {
        return price;
    }

    public void setPrice(PriceModel price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}