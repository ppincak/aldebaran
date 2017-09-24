package com.aldebaran.utils;

import com.aldebaran.utils.enums.ComparisonOperator;


// NOTE(peter.pincak) figure out a better name
public interface Searchable<T> {

    String getSearchProperty();

    ComparisonOperator getOperator();

    T getSearchValue();
}
