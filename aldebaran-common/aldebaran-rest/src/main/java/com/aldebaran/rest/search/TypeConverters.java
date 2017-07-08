package com.aldebaran.rest.search;

import java.util.HashMap;
import java.util.Map;


public final class TypeConverters {

    private static final Map<Class<?>, TypeConverter> typeConverters = new HashMap<>();

    static {
        typeConverters.put(Long.class, new ListTypeConverter.LongListTypeConverter());
        typeConverters.put(String.class, new ListTypeConverter.StringListTypeConverter());
    }

    public static TypeConverter get(Class<?> converter) {
        return typeConverters.get(converter);
    }
}
