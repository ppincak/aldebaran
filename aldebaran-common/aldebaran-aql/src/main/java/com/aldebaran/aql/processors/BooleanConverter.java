package com.aldebaran.aql.processors;


public class BooleanConverter implements ValueConverter<Boolean> {

    // TODO figure out and easy check
    @Override
    public boolean shouldConvert(String value) {
        return false;
    }

    @Override
    public Boolean convert(String value) {
        return Boolean.valueOf(value);
    }
}
