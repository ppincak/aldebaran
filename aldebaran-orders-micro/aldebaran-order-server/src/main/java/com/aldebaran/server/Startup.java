package com.aldebaran.server;

import com.aldebaran.server.config.SpringConfiguration;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.SpringApplication;


public class Startup {

    static {
        DefaultExports.initialize();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringConfiguration.class, args);
    }
}