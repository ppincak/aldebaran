package com.aldebaran.omanager.core.descriptors;

import com.aldebaran.rest.search.SearchDescriptor;
import com.aldebaran.rest.search.TypeOperators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public final class CustomerSearchDescriptors {

    public static final Map<String, SearchDescriptor> descriptors = new HashMap<>();
    public static final Set<String> orderProperties = new HashSet<>();

    static {
        addDescriptor("firstName", String.class,"firstName");
        addDescriptor("lastName", String.class, "lastName");
        addDescriptor("email", String.class, "email");

        orderProperties.add("id");
        orderProperties.add("firstName");
        orderProperties.add("lastName");
        orderProperties.add("email");
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
