package com.aldebaran.order.core.model;

import java.util.Map;


public class CustomerOrderProductsMap {

    protected final Map<String, Object> map;

    public CustomerOrderProductsMap(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}