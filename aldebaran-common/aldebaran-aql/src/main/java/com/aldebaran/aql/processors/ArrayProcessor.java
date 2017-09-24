package com.aldebaran.aql.processors;


public class ArrayProcessor implements ValueProcessor<Object[]> {

    @Override
    public boolean shouldProcess(String value) {
        return value.charAt(0) == '[';
    }

    @Override
    public Object[] process(String value) {
        return null;
    }
}