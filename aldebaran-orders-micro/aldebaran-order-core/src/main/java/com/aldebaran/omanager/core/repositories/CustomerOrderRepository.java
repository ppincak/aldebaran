package com.aldebaran.omanager.core.repositories;

import com.aldebaran.omanager.core.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{

}