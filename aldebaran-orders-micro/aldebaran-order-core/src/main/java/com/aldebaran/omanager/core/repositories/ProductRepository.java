package com.aldebaran.omanager.core.repositories;

import com.aldebaran.omanager.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getByName(String name);

    @Query("SELECT p FROM Product p " +
           "WHERE p.id IN(:productIds)")
    List<Product> getByProductIds(@Param("productIds") Set<Long> productIds);

}
