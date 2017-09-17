package com.aldebaran.aql.nodes;

import com.aldebaran.aql.operators.ComparisonOperator;


public class ExpressionNode<T> implements AqlNode {

    private final String searchProperty;
    private final ComparisonOperator operator;
    private final T searchValue;

    public ExpressionNode(String searchProperty,
                          T searchValue,
                          ComparisonOperator operator) {
        this.searchProperty = searchProperty;
        this.searchValue = searchValue;
        this.operator = operator;
    }

    public String getSearchProperty() {
        return searchProperty;
    }

    public ComparisonOperator getOperator() {
        return operator;
    }

    public T getSearchValue() {
        return searchValue;
    }
}