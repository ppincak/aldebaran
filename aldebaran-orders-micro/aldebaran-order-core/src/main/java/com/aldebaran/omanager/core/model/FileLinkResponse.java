package com.aldebaran.omanager.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FileLinkResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
