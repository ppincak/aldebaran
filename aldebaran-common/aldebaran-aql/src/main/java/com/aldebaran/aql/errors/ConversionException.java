package com.aldebaran.aql.errors;

import com.aldebaran.aql.processors.ValueConverter;


public class ConversionException extends RuntimeException {

    private final Class<? extends ValueConverter> processorClazz;
    private final String processingValue;

    public ConversionException(Class<? extends ValueConverter> processorClazz,
                               String processingValue,
                               Throwable cause) {
        super("Failed to convert value: \"" +processingValue + "\"", cause);

        this.processorClazz = processorClazz;
        this.processingValue = processingValue;
    }

    public Class<? extends ValueConverter> getProcessorClazz() {
        return processorClazz;
    }

    public String getProcessingValue() {
        return processingValue;
    }
}
