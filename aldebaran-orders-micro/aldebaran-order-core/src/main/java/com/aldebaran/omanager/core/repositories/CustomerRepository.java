package com.aldebaran.omanager.core.repositories;

import com.aldebaran.omanager.core.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer getByEmail(String email);

    @Query("SELECT COUNT(c) FROM Customer c " +
           "WHERE id=:customerId")
    Long ordersCount(@Param("customerId") Long customerId);

    Page<Customer> findAll(Specification<Customer> specification, Pageable pageable);
}