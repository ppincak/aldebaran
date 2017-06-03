package com.aldebaran.data.domain;

import com.aldebaran.data.descriptors.Identifiable;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class BaseDomain implements Identifiable<Long>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}