package com.aldebaran.order.core.repositories;

import com.aldebaran.order.core.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{

}