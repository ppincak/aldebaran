package com.aldebaran.aql.processors;

import java.util.ArrayList;
import java.util.List;


public final class DefaultConverters {

    public static List<ValueConverter> get() {
        List<ValueConverter> valueConverters = new ArrayList<>();
        valueConverters.add(new StringConverter());
        valueConverters.add(new ArrayConverter());
        valueConverters.add(new DoubleConverter());
        return valueConverters;
    }
}
