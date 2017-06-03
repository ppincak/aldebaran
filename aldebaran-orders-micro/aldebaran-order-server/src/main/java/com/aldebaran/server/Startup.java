package com.aldebaran.server;

import com.aldebaran.server.configuration.SpringConfiguration;
import org.springframework.boot.SpringApplication;


public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfiguration.class, args);
    }
}