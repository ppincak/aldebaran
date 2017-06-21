package com.aldebaran.chassis.discovery;

import org.springframework.context.ApplicationEvent;

import java.util.List;


public class ConsulServiceChangeEvent extends ApplicationEvent {

    private final String serviceName;
    private final List<String> services;

    public ConsulServiceChangeEvent(Object source, String serviceName, List<String> services) {
        super(source);
        this.serviceName = serviceName;
        this.services = services;
    }

    public List<String> getServices() {
        return services;
    }
}
