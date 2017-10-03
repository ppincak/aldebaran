package com.aldebaran.rest.search;

import com.aldebaran.utils.Searchable;
import com.aldebaran.utils.enums.ComparisonOperator;


public class SearchCriterion<T> implements Searchable<T> {

    private final SearchOperator searchOperator;
    private final String searchProperty;
    private final T searchValue;

    public SearchCriterion(SearchOperator searchOperator,
                           String searchProperty,
                           T searchValue) {
        this.searchOperator = searchOperator;
        this.searchProperty = searchProperty;
        this.searchValue = searchValue;
    }

    public SearchOperator getSearchOperator() {
        return searchOperator;
    }

    @Override
    public String getSearchProperty() {
        return searchProperty;
    }

    @Override
    public ComparisonOperator getOperator() {
        return ComparisonOperator.valueOf(searchOperator.toString());
    }

    @Override
    public T getSearchValue() {
        return searchValue;
    }
}
