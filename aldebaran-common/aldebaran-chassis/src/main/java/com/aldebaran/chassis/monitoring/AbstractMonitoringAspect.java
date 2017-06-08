package com.aldebaran.chassis.monitoring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class AbstractMonitoringAspect<T> {

    protected final Map<String, T> monitors;

    public AbstractMonitoringAspect() {
        monitors = new ConcurrentHashMap<>();
    }
}