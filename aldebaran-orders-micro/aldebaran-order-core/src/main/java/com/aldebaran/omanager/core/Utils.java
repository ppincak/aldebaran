package com.aldebaran.omanager.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Utils {

    public static String[] orderBy(Set<String> available, String[] givenProperties) {
        List<String> validProperties = new ArrayList<>();
        for(String giveProperty: givenProperties) {
            if(available.contains(giveProperty)) {
                validProperties.add(giveProperty);
            }
        }
        return validProperties.toArray(new String[validProperties.size()]);
    }
}
