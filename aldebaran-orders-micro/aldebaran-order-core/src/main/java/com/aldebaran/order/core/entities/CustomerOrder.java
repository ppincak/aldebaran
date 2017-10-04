package com.aldebaran.order.core.entities;

import com.aldebaran.data.domain.Price;
import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = CustomerOrder.tableName)
public class CustomerOrder extends TrackableBaseDomain {

    public static final String tableName = "customer_order";

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Embedded
    private Price priceSum;

    @OneToMany(mappedBy = "customerOrder",
               cascade = CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<CustomerOrderProduct> orderProducts;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Price getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Price priceSum) {
        this.priceSum = priceSum;
    }

    public List<CustomerOrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<CustomerOrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}