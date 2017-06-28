package com.aldebaran.rest.search;

import java.util.HashMap;
import java.util.Map;


public final class TypeConverters {

    private static final Map<Class<?>, TypeConverter> typeConverters = new HashMap<>();

    static {
        typeConverters.put(Long.class, new ListTypeConverter.LongListTypeConverter());
    }

    public static <TSource, TTarget> TypeConverter<TSource, TTarget> get(Class<TTarget> converter) {
        return null;
    }
}
