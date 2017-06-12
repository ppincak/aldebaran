package com.aldebaran.chassis.discovery;

import java.util.Map;


public class ServiceDiscoveryStub implements ServiceDiscovery {

    private Map<String, ServiceDescription> services;

    public ServiceDiscoveryStub(Map<String, ServiceDescription> services) {
        this.services = services;
    }

    @Override
    public ServiceDescription discover(String serviceName) {
        return services.get(serviceName);
    }
}