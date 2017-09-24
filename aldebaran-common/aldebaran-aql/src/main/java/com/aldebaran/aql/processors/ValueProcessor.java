package com.aldebaran.aql.processors;

import com.aldebaran.aql.errors.ProcessingException;

public interface ValueProcessor<T> {

    boolean shouldProcess(String value);

    T process(String value);
}