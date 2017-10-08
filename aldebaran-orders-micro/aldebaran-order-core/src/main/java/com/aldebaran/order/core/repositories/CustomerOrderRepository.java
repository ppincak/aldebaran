package com.aldebaran.order.core.repositories;

import com.aldebaran.order.core.entities.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Repository
public interface CustomerOrderRepository extends JpaSearchRepository<CustomerOrder, Long> {

    @Query("SELECT co FROM CustomerOrder co " +
           "WHERE customer.id=:customerId")
    Page<CustomerOrder> getByCustomerId(@Param("customerId") Long customerId, Pageable pageable);

    @Query("SELECT new map(co.id as orderId, " +
                          "p.id as productId, " +
                          "p.name as productName, " +
                          "op.quantity as productQuantity, " +
                          "p.price as productPrice) FROM CustomerOrder co " +
           "JOIN co.orderProducts op " +
           "JOIN op.product p " +
           "WHERE co.id IN (:customerOrderIds)")
    List<Map<String, Object>> getByCustomerOrderIds(@Param("customerOrderIds") Collection<Long> customerOrderIds);
}