package com.aldebaran.rest.search;


public interface TypeConverter<TSource, TTarget> {

    TTarget convert(TSource source);
}