package com.aldebaran.chassis.discovery;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.Registration;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;


@Component
@Conditional(ConsulCondition.ConsulEnabledCondition.class)
public class ConsulDeregistrationBean implements DisposableBean {

    @Autowired
    private Consul consul;

    @Autowired
    private Registration consulRegistration;

    @Override
    public void destroy() throws Exception {
        consul
            .agentClient()
            .deregister(consulRegistration.getId());
    }
}
