package com.aldebaran.omanager.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;


public class CustomerOrderRequest {

    @NotEmpty
    @JsonProperty
    private Set<OrderProduct> products;

    public Set<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProduct> products) {
        this.products = products;
    }
}
