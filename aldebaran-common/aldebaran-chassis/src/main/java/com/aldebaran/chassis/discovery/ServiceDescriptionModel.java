package com.aldebaran.chassis.discovery;


public class ServiceDescriptionModel implements ServiceDescription {

    public static ServiceDescription create() {
        return null;
    }

    private String protocol;
    private String host;
    private Integer port;

    public ServiceDescriptionModel(String protocol, String host, Integer port) {
        this.protocol = protocol;
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

    //NOTE(peter.pincak) validate and rethink
    @Override
    public String assembleUrl() {
        return protocol + host + ":" + port;
    }
}