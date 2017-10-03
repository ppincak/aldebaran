package com.aldebaran.aql.processors;


public class ArrayConverter implements ValueConverter<Object[]> {

    private DoubleConverter doubleProcessor;
    private StringConverter stringProcessor;

    public ArrayConverter() {
    }

    @Override
    public boolean shouldConvert(String value) {
        return value.charAt(0) == '[';
    }

    @Override
    public Object[] convert(String value) {
        String rawValues = value.substring(1, value.length() - 1);
        return rawValues.split(",");
    }
}