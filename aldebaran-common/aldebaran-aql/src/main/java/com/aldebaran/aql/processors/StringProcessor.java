package com.aldebaran.aql.processors;



public class StringProcessor implements ValueProcessor<String> {

    @Override
    public boolean shouldProcess(String value) {
        return value.charAt(0) == '"';
    }

    @Override
    public String process(String value) {
        return value.substring(1, value.length() -1);
    }
}