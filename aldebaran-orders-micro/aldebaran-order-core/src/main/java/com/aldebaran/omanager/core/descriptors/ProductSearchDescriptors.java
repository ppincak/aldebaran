package com.aldebaran.omanager.core.descriptors;

import com.aldebaran.rest.search.SearchDescriptor;
import com.aldebaran.rest.search.TypeOperators;

import java.math.BigDecimal;
import java.util.*;


public final class ProductSearchDescriptors {

    public static final Map<String, SearchDescriptor> descriptors = new HashMap<>();
    public static final Set<String> orderProperties = new HashSet<>();

    static {
        addDescriptor("id", Long.class, "id");
        addDescriptor("name", String.class, "name");
        addDescriptor("preTaxPrice", BigDecimal.class, "price.preTax");
        addDescriptor("afterTaxPrice", BigDecimal.class, "price.afterTax");

        orderProperties.addAll(Arrays.asList("id", "name"));
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
