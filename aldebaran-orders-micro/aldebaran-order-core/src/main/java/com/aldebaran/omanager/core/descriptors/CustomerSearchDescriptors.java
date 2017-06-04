package com.aldebaran.omanager.core.descriptors;

import com.aldebaran.rest.search.SearchDescriptor;
import com.aldebaran.rest.search.TypeOperators;

import java.util.*;


public final class CustomerSearchDescriptors {

    private static final Map<String, SearchDescriptor> descriptors = new HashMap<>();
    private static final Set<String> orderProperties = new HashSet<>();

    static {
        addDescriptor("id", Long.class,"id");
        addDescriptor("firstName", String.class,"firstName");
        addDescriptor("lastName", String.class, "lastName");
        addDescriptor("email", String.class, "email");

        orderProperties.addAll(Arrays.asList("id", "firstName", "lastName", "email"));
    }

    public static Map<String, SearchDescriptor> getDescriptors() {
        return descriptors;
    }

    public static Set<String> getOrderProperties() {
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
