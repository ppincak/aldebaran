package com.aldebaran.aql.processors;

import java.util.ArrayList;
import java.util.List;


public final class DefaultProcessors {

    public static List<ValueProcessor> get() {
        List<ValueProcessor> valueProcessors = new ArrayList<>();
        valueProcessors.add(new StringProcessor());
        valueProcessors.add(new ArrayProcessor());
        valueProcessors.add(new DoubleProcessor());
        return valueProcessors;
    }
}
