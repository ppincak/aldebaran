package com.aldebaran.order.core.repositories;

import com.aldebaran.order.core.entities.CustomerOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerOrderProductRepository extends JpaRepository<CustomerOrderProduct, Long> {

    @Modifying
    @Query("DELETE FROM CustomerOrderProduct c " +
           "WHERE c.customerOrder.id=:orderId")
    void deleteByOrderId(@Param("orderId") Long customerOrderId);
}