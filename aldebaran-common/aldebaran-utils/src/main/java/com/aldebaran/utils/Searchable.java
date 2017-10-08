package com.aldebaran.utils;

import com.aldebaran.utils.enums.ComparisonOperator;


public interface Searchable<T> {

    String getSearchProperty();

    ComparisonOperator getOperator();

    T getSearchValue();
}
