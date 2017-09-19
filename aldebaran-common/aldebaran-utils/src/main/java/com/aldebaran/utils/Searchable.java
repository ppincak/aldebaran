package com.aldebaran.utils;

import com.aldebaran.utils.operators.ComparisonOperator;


// TODO figure out a better name
public interface Searchable<T> {

    String getSearchProperty();

    ComparisonOperator getOperator();

    T getSearchValue();
}
