package com.aldebaran.rest.search;

import java.util.*;


public abstract class AbstractSearchDescriptors {

    final Map<String, SearchDescriptor> searchDescriptors;
    final Map<String, OrderDescriptor> orderDescriptors;

    protected AbstractSearchDescriptors() {
        searchDescriptors = new HashMap<>();
        orderDescriptors = new HashMap<>();

        init();
    }

    protected abstract void init();

    protected void addOrderDescriptor(String propertyName) {
        orderDescriptors.put(propertyName,
                             new OrderDescriptor(propertyName, propertyName));
    }

    protected void addOrderDescriptors(String... propertyNames) {
        for(String propertyName: propertyNames) {
            addOrderDescriptor(propertyName);
        }
    }

    protected void addOrderDescriptor(String propertyName,
                                      String resultProperty) {
        orderDescriptors.put(propertyName,
                             new OrderDescriptor(propertyName, resultProperty));
    }

    protected void addDescriptor(String propertyName,
                                 Class<? extends Comparable<?>> resultType,
                                 SearchOperator... additionalSearchOperators) {
        EnumSet<SearchOperator> searchOperators = TypeOperators.getOperators(resultType);
        searchOperators.addAll(Arrays.asList(additionalSearchOperators));
        searchDescriptors
                .put(propertyName,
                        new SearchDescriptor(propertyName,
                                             resultType,
                                             propertyName,
                                             searchOperators));
    }

    protected void addDescriptor(String propertyName,
                                 Class<? extends Comparable<?>> resultType) {

        searchDescriptors
                .put(propertyName,
                     new SearchDescriptor(propertyName,
                                          resultType,
                                          propertyName,
                                          TypeOperators.getOperators(resultType)));
    }

    protected void addDescriptor(String propertyName,
                                 Class<? extends Comparable<?>> resultType,
                                 String resultProperty) {
        searchDescriptors
                .put(propertyName,
                     new SearchDescriptor(propertyName,
                                          resultType,
                                          resultProperty,
                                          TypeOperators.getOperators(resultType)));
    }

    public Map<String, SearchDescriptor> getSearchDescriptors() {
        return searchDescriptors;
    }

    public Map<String, OrderDescriptor> getOrderDescriptors() {
        return orderDescriptors;
    }
}