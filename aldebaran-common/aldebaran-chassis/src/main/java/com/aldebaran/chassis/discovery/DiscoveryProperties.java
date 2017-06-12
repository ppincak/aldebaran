package com.aldebaran.chassis.discovery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class DiscoveryProperties {

    @Value("${discovery.serviceName}")
    private String serviceName;

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