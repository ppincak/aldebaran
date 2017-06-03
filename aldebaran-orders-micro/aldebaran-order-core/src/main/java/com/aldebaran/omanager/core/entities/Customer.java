package com.aldebaran.omanager.core.entities;

import com.aldebaran.data.domain.TrackableBaseDomain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = Customer.tableName)
public class Customer extends TrackableBaseDomain {

    public static final String tableName = "customer";

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "customer",
               fetch = FetchType.LAZY)
    private List<CustomerOrder> customerOrders;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }
}