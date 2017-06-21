package com.aldebaran.chassis.discovery;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import java.util.ArrayList;
import java.util.List;


public class DynamicServerList implements ServerList<Server> {

    private List<Server> updatedServers;

    public void setUpdatedServers(List<Server> updatedServers) {
        this.updatedServers = updatedServers;
    }

    @Override
    public List<Server> getInitialListOfServers() {
        return new ArrayList<>();
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
        return updatedServers == null ? new ArrayList<>() : updatedServers;
    }
}