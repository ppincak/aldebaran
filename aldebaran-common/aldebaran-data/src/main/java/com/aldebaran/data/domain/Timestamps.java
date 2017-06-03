package com.aldebaran.data.domain;

import com.aldebaran.data.descriptors.Trackable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Instant;


@Embeddable
public class Timestamps implements Serializable, Trackable {

    @Column(name = "created_at",
            insertable = false,
            updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at",
            insertable = false,
            updatable = false)
    private Instant updatedAt;

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}