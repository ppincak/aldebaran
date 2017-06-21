package com.aldebaran.chassis.discovery;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;


public final class DynamicLoadBalancer {

    private DynamicServerList dynamicServerList;
    private ZoneAwareLoadBalancer<Server> zoneAwareLoadBalancer;

    public DynamicLoadBalancer(DynamicServerList dynamicServerList,
                               ZoneAwareLoadBalancer<Server> zoneAwareLoadBalancer) {
        this.dynamicServerList = dynamicServerList;
        this.zoneAwareLoadBalancer = zoneAwareLoadBalancer;
    }

    public DynamicServerList getDynamicServerList() {
        return dynamicServerList;
    }

    public void setDynamicServerList(DynamicServerList dynamicServerList) {
        this.dynamicServerList = dynamicServerList;
    }

    public ZoneAwareLoadBalancer<Server> getZoneAwareLoadBalancer() {
        return zoneAwareLoadBalancer;
    }

    public void setZoneAwareLoadBalancer(ZoneAwareLoadBalancer<Server> zoneAwareLoadBalancer) {
        this.zoneAwareLoadBalancer = zoneAwareLoadBalancer;
    }
}
