package com.aldebaran.order.core.entities;

import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.*;


@Entity
@Table(name = ProductFileLink.tableName)
public class ProductFileLink extends TrackableBaseDomain {

    public static final String tableName = "product_file_link";

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_link_id")
    private FileLink fileLink;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public FileLink getFileLink() {
        return fileLink;
    }

    public void setFileLink(FileLink fileLink) {
        this.fileLink = fileLink;
    }
}