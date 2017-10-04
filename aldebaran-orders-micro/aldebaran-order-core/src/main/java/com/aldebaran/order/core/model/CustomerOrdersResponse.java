package com.aldebaran.order.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class CustomerOrdersResponse {

    @JsonProperty
    private List<CustomerOrderResponse> orders;

    public void addOrder(CustomerOrderResponse orderResponse) {
        if(orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(orderResponse);
    }

    public List<CustomerOrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<CustomerOrderResponse> orders) {
        this.orders = orders;
    }
}