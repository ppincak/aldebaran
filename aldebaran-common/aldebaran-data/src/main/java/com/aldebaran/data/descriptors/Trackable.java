package com.aldebaran.data.descriptors;

import java.time.Instant;


public interface Trackable  {

    Instant getCreatedAt();

    void setCreatedAt(Instant time);

    Instant getUpdatedAt();

    void setUpdatedAt(Instant time);
}