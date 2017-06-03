package com.aldebaran.data.domain;

import com.aldebaran.data.descriptors.Trackable;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.time.Instant;


@MappedSuperclass
public class TrackableBaseDomain extends BaseDomain implements Trackable {

    @Embedded
    private Timestamps timestamps;

    public TrackableBaseDomain() {
        this.timestamps = new Timestamps();
    }

    @PostPersist
    @PostUpdate
    public void iniTimestamps() {
        Instant now = Instant.now();
        if(timestamps.getCreatedAt() == null) {
            timestamps.setCreatedAt(now);
        }
        timestamps.setUpdatedAt(now);
    }

    @Override
    public Instant getCreatedAt() {
        return timestamps.getCreatedAt();
    }

    @Override
    public void setCreatedAt(Instant time) {
        timestamps.setCreatedAt(time);
    }

    @Override
    public Instant getUpdatedAt() {
        return timestamps.getUpdatedAt();
    }

    @Override
    public void setUpdatedAt(Instant time) {
        timestamps.setUpdatedAt(time);
    }

    public Timestamps getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Timestamps timestamps) {
        this.timestamps = timestamps;
    }
}