package com.aldebaran.rest.hal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public abstract class HalResourceCollection<T> extends ResourceSupport {

    private final Map<String, Collection<T>> embedded = new HashMap<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("_embedded")
    public Map<String, Collection<T>> getEmbeddedResources() {
        return embedded;
    }

    public void embedResource(String relationship, Collection<T> items) {
        embedded.put(relationship, items);
    }
}
