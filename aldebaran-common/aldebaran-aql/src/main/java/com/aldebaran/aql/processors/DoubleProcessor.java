package com.aldebaran.aql.processors;

public class DoubleProcessor implements ValueProcessor<Double> {

    @Override
    public boolean shouldProcess(String value) {
        return value.contains(".");
    }
    
    @Override
    public Double process(String value) {
        return Double.parseDouble(value);
    }
}