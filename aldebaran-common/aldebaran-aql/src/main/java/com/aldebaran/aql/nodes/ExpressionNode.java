package com.aldebaran.aql.nodes;

import com.aldebaran.utils.Searchable;
import com.aldebaran.utils.enums.ComparisonOperator;


public class ExpressionNode<T> implements AqlNode, Searchable<T> {

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

    @Override
    public String getSearchProperty() {
        return searchProperty;
    }

    @Override
    public ComparisonOperator getOperator() {
        return operator;
    }

    @Override
    public T getSearchValue() {
        return searchValue;
    }
}