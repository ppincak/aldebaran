package com.aldebaran.rest;

import com.aldebaran.rest.search.OrderDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RestUtils {

    public static String[] orderBy(String[] orderByProperties, Map<String, OrderDescriptor> orderDescriptors) {
        List<String> validProperties = new ArrayList<>();
        for(String orderByProperty: orderByProperties) {
            OrderDescriptor orderDescriptor = orderDescriptors.get(orderByProperty);
            if(orderDescriptor != null) {
                validProperties.add(orderDescriptor.getResultProperty());
            }
        }
        return validProperties.toArray(new String[validProperties.size()]);
    }
}
