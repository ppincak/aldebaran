package com.aldebaran.aql.errors;

import com.aldebaran.aql.processors.ValueProcessor;

public class ProcessingException extends RuntimeException {

    private final Class<? extends ValueProcessor> processorClazz;
    private final String processingValue;

    public ProcessingException(Class<? extends ValueProcessor> processorClazz,
                               String processingValue,
                               Throwable cause) {
        super("Failed to process value: \"" +processingValue + "\"", cause);

        this.processorClazz = processorClazz;
        this.processingValue = processingValue;
    }

    public Class<? extends ValueProcessor> getProcessorClazz() {
        return processorClazz;
    }

    public String getProcessingValue() {
        return processingValue;
    }
}
