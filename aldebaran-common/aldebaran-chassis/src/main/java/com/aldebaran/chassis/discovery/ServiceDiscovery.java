package com.aldebaran.chassis.discovery;


public interface ServiceDiscovery {

    ServiceDescription discover(String serviceName);
}
