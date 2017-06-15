package com.aldebaran.chassis.discovery;

import com.aldebaran.utils.FormattingUtils;
import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


@Configuration
@Conditional(ConsulCondition.class)
public class ConsulConfigurationConfiguration {

    @Autowired
    private DiscoveryProperties discoveryProperties;

    @Bean
    public Consul consul() {
        return Consul
                .builder()
                .withHostAndPort(
                        HostAndPort.fromParts(discoveryProperties.getConsulHost(),
                                              discoveryProperties.getConsulPort()))
                .build();
    }

    @Bean
    @Autowired
    public Registration registration(Consul consul) {
        AgentClient agentClient = consul.agentClient();

        URL url;
        try {
            url = new URL("http",
                          discoveryProperties.getServiceHost(),
                          discoveryProperties.getServicePort(),
                          discoveryProperties.getHealthCheckPath());
        } catch (MalformedURLException e) {
            throw new ConsulException("Consul initialization failed");
        }

        Registration.RegCheck httpCheck =
                Registration
                        .RegCheck
                        .http(url.toExternalForm(),
                              FormattingUtils.parseExpression(discoveryProperties.getHealthCheckInterval()));

        Registration registration =
                ImmutableRegistration
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .name(discoveryProperties.getServiceName())
                        .address(discoveryProperties.getServiceHost())
                        .port(discoveryProperties.getServicePort())
                        .addChecks(httpCheck)
                        .build();

        agentClient.register(registration);
        return registration;
    }
}