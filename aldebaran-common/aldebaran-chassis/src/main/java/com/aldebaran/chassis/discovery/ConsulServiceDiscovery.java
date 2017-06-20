package com.aldebaran.chassis.discovery;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Conditional(ConsulEnabledCondition.class)
public class ConsulServiceDiscovery {

    @Autowired
    private Consul consul;

    public void discover() {
        Map<String, Service> services = consul.agentClient().getServices();
        for(Service service: services.values()) {

        }
    }
}