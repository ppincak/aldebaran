package com.aldebaran.chassis.discovery;

import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.orbitz.consul.Consul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class RibbonServiceDiscovery implements ServiceDiscovery {

    @Autowired
    private Consul consul;

    @Autowired
    private DiscoveryProperties discoveryProperties;

    private final Map<String, ZoneAwareLoadBalancer> loadBalancers;

    public RibbonServiceDiscovery() {
        loadBalancers = new ConcurrentHashMap<>();
    }

    @PostConstruct
    private void init() {
        for(String discoveryDepenedy: discoveryProperties.getDiscoveryDependencies()) {
            ZoneAwareLoadBalancer loadBalancer =
                    LoadBalancerBuilder
                        .newBuilder()
                        .buildDynamicServerListLoadBalancer();

            loadBalancers.put(discoveryDepenedy, loadBalancer);
        }
    }

    @Scheduled(fixedDelay = 10000, fixedRate = 10000)
    public void refresh() {

    }

    @Override
    public ServiceDescription discover(String serviceName) {
        ZoneAwareLoadBalancer loadBalancer = loadBalancers.get(serviceName);
        if(loadBalancer == null) {
            return null;
        }
        loadBalancer.chooseServer();
        return null;
    }
}