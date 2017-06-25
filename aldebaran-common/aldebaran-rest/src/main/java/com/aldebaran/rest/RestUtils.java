package com.aldebaran.rest;

import com.aldebaran.rest.search.OrderDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RestUtils {

    public static boolean hasApiKey(String[] apiKeys, String apiKey) {
        for(String key: apiKeys) {
            if(key.equals(apiKey)) {
                return true;
            }
        }
        return false;
    }

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
