package com.aldebaran.aql.processors;



public class StringConverter implements ValueConverter<String> {

    @Override
    public boolean shouldConvert(String value) {
        return value.charAt(0) == '"';
    }

    @Override
    public String convert(String value) {
        return value.substring(1, value.length() -1);
    }
}