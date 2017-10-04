package com.aldebaran.order.core.repositories;

import com.aldebaran.order.core.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaSearchRepository<Customer, Long> {

    Customer getByEmail(String email);

    @Query("SELECT COUNT(c) FROM Customer c " +
           "WHERE id=:customerId")
    Long ordersCount(@Param("customerId") Long customerId);
}