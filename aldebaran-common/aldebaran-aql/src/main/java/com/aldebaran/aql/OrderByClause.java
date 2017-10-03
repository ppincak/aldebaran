package com.aldebaran.aql;

import com.aldebaran.utils.enums.OrderDirection;


public final class OrderByClause {

    private final String[] properties;
    private final OrderDirection orderDirection;

    public OrderByClause(String[] properties, OrderDirection orderDirection) {
        this.properties = properties;
        this.orderDirection = orderDirection;
    }

    public String[] getProperties() {
        return properties;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }
}