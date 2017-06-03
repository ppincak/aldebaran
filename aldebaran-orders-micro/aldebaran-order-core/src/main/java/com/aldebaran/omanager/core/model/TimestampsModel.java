package com.aldebaran.omanager.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class TimestampsModel {

    @JsonProperty
    private LocalDateTime createdAt;

    @JsonProperty
    private LocalDateTime updatedAt;

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = LocalDateTime.ofInstant(createdAt, ZoneOffset.UTC);
}

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = LocalDateTime.ofInstant(updatedAt, ZoneOffset.UTC);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
