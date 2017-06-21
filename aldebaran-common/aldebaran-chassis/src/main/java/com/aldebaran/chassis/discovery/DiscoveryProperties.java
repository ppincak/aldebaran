package com.aldebaran.chassis.discovery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DiscoveryProperties {

    @Value("${discovery.service.name}")
    private String serviceName;

    @Value("${discovery.service.port}")
    private Integer servicePort;

    @Value("${discovery.services}")
    private String[] discoveryDependencies;

    @Value("${discovery.healthCheckPath}")
    private String healthCheckPath;

    @Value("${discovery.healthCheckInterval}")
    private String healthCheckInterval;

    @Value("${discovery.consul.enabled}")
    private Boolean consulEnabled;

    @Value("${discovery.consul.port}")
    private Integer consulPort;

    @Value("${discovery.consul.host}")
    private String consulHost;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServicePort() {
        return servicePort;
    }

    public void setServicePort(Integer servicePort) {
        this.servicePort = servicePort;
    }

    public String[] getDiscoveryServices() {
        return discoveryDependencies;
    }

    public void setDiscoveryDependencies(String[] discoveryDependencies) {
        this.discoveryDependencies = discoveryDependencies;
    }

    public String getHealthCheckPath() {
        return healthCheckPath;
    }

    public void setHealthCheckPath(String healthCheckPath) {
        this.healthCheckPath = healthCheckPath;
    }

    public String getHealthCheckInterval() {
        return healthCheckInterval;
    }

    public void setHealthCheckInterval(String healthCheckInterval) {
        this.healthCheckInterval = healthCheckInterval;
    }

    public Boolean getConsulEnabled() {
        return consulEnabled;
    }

    public void setConsulEnabled(Boolean consulEnabled) {
        this.consulEnabled = consulEnabled;
    }

    public Integer getConsulPort() {
        return consulPort;
    }

    public void setConsulPort(Integer consulPort) {
        this.consulPort = consulPort;
    }

    public String getConsulHost() {
        return consulHost;
    }

    public void setConsulHost(String consulHost) {
        this.consulHost = consulHost;
    }
}