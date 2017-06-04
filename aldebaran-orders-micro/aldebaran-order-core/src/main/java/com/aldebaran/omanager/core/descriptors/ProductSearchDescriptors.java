package com.aldebaran.omanager.core.descriptors;

import com.aldebaran.rest.search.SearchDescriptor;
import com.aldebaran.rest.search.TypeOperators;

import java.math.BigDecimal;
import java.util.*;


public final class ProductSearchDescriptors {

    private static final Map<String, SearchDescriptor> descriptors = new HashMap<>();
    private static final Set<String> orderProperties = new HashSet<>();

    static {
        addDescriptor("id", Long.class, "id");
        addDescriptor("name", String.class, "name");
        addDescriptor("preTaxPrice", BigDecimal.class, "price.preTax");
        addDescriptor("afterTaxPrice", BigDecimal.class, "price.afterTax");

        orderProperties.addAll(Arrays.asList("id", "name"));
    }

    public static Map<String, SearchDescriptor> getDescriptors() {
        return descriptors;
    }

    public static final Set<String> getOrderProperties() {
        return orderProperties;
    }

    private static void addDescriptor(String propertyName,
                                      Class<? extends Comparable<?>> resultType,
                                      String resultProperty) {
        descriptors.put(propertyName,
                new SearchDescriptor(propertyName,
                        resultType,
                        resultProperty,
                        TypeOperators.getOperators(resultType)));
    }
}
