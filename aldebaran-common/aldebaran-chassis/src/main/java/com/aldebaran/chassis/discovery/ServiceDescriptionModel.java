package com.aldebaran.chassis.discovery;


public class ServiceDescriptionModel implements ServiceDescription {

    public static ServiceDescription create() {
        return null;
    }

    private String host;
    private Integer port;

    public ServiceDescriptionModel(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Integer port() {
        return port;
    }

    @Override
    public String host() {
        return host;
    }

    @Override
    public String assembleUrl() {
        return null;
    }
}