package com.aldebaran.order.core.entities;

import com.aldebaran.data.domain.Price;
import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.*;


@Entity
@Table(name = CustomerOrderProduct.tableName)
public class CustomerOrderProduct extends TrackableBaseDomain {

    public static final String tableName = "customer_order_product";

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_id",
            insertable = false,
            updatable = false)
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Embedded
    private Price price;

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}