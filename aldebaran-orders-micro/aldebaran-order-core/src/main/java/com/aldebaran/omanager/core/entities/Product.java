package com.aldebaran.omanager.core.entities;

import com.aldebaran.data.descriptors.Describable;
import com.aldebaran.data.descriptors.Nameable;
import com.aldebaran.data.domain.Price;
import com.aldebaran.data.domain.TrackableBaseDomain;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = Product.tableName)
public class Product extends TrackableBaseDomain implements Nameable, Describable {

    public static final String tableName = "product";

    @Column(name = "`name`",
            nullable = false,
            unique = true)
    private String name;

    @Column(name = "description",
            nullable = false)
    @Type(type = "text")
    private String description;

    @Embedded
    private Price price;

    @Column(name = "code")
    private String code;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}