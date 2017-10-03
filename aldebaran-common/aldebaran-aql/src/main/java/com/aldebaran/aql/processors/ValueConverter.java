package com.aldebaran.aql.processors;


public interface ValueConverter<T> {

    boolean shouldConvert(String value);

    T convert(String value);
}