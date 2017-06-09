package com.aldebaran.chassis.discovery;

import org.springframework.stereotype.Component;


@Component
public class DiscoveryProperties {

    private String serviceName;

    private String healthCheckPath;

    private Integer healthCheckInterval;

    private Boolean consulEnabled;

    private Integer consulPort;

    private String consulHost;
}