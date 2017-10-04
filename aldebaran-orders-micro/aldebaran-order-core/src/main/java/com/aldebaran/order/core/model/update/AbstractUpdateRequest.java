package com.aldebaran.order.core.model.update;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractUpdateRequest implements UpdateRequest {

    protected final Map<String, Object> map;

    public AbstractUpdateRequest() {
        this.map = new HashMap<>();;
    }

    @Override
    @JsonAnySetter
    public void setProperty(String key, Object value) {
        this.map.put(key, value);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}