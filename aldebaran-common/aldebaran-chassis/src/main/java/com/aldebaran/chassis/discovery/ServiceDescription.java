package com.aldebaran.chassis.discovery;


public interface ServiceDescription {

    Integer port();

    String host();

    String assembleUrl();
}