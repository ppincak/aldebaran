package com.aldebaran.chassis.discovery;

import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.orbitz.consul.Consul;
import com.orbitz.consul.cache.ServiceHealthCache;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.model.health.ServiceHealth;
import com.orbitz.consul.option.QueryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Conditional(ConsulCondition.Enabled.class)
public class LoadBalancingServiceDiscovery implements ServiceDiscovery {

    @Autowired
    private Consul consul;

    @Autowired
    private DiscoveryProperties discoveryProperties;

    private final Map<String, DynamicLoadBalancer> loadBalancers;

    public LoadBalancingServiceDiscovery() {
        loadBalancers = new ConcurrentHashMap<>();
    }

    @PostConstruct
    private void init() {
        for(String serviceName: discoveryProperties.getDiscoveryServices()) {
            DynamicServerList serverList = new DynamicServerList();

            ZoneAwareLoadBalancer loadBalancer =
                    LoadBalancerBuilder
                            .newBuilder()
                            .withDynamicServerList(serverList)
                            .buildDynamicServerListLoadBalancer();

            loadBalancers.put(serviceName, new DynamicLoadBalancer(serverList, loadBalancer));

            ServiceHealthCache serviceHealthCache =
                    ServiceHealthCache.newCache(consul.healthClient(),
                                                serviceName,
                                               true,
                                                QueryOptions.BLANK,
                                               60);

            serviceHealthCache.addListener(newServices -> {
                List<Server> servers = new ArrayList<>();

                for(ServiceHealth serviceHealth: newServices.values()) {
                    Service service = serviceHealth.getService();
                    servers.add(new Server(service.getAddress() + ":"  + service.getPort()));
                }
                DynamicLoadBalancer dynamicLoadBalancer  = loadBalancers.get(serviceName);
                dynamicLoadBalancer.getDynamicServerList().setUpdatedServers(servers);
            });

            try {
                serviceHealthCache.start();
            } catch (Exception e) {
                throw new ConsulException("Failed to start consul service search");
            }
        }
    }

    @Override
    public ServiceDescription discover(String serviceName) {
        DynamicLoadBalancer loadBalancer = loadBalancers.get(serviceName);
        if(loadBalancer == null) {
            return null;
        }
        Server server = loadBalancer.getZoneAwareLoadBalancer().chooseServer();
        if(server == null) {
            return null;
        }

        return new ServiceDescriptionModel("http://",
                                           server.getHost(),
                                           server.getPort());
    }
}