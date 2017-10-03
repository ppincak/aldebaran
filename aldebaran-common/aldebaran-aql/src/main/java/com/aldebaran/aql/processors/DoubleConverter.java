package com.aldebaran.aql.processors;

public class DoubleConverter implements ValueConverter<Double> {

    @Override
    public boolean shouldConvert(String value) {
        return value.contains(".");
    }
    
    @Override
    public Double convert(String value) {
        return Double.parseDouble(value);
    }
}