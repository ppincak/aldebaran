package com.aldebaran.omanager.core.entities;

import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.*;


@Entity
@Table(name = ProductImage.tableName)
public class ProductImage extends TrackableBaseDomain {

    public static final String tableName = "product_image";

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image")
    private Image image;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}