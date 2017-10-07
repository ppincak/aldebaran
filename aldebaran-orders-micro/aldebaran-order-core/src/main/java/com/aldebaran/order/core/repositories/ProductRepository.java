package com.aldebaran.order.core.repositories;

import com.aldebaran.order.core.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProductRepository extends JpaSearchRepository<Product, Long> {

    Long countByName(String name);

    Long countByCode(String code);

    @Query("SELECT p FROM Product p " +
           "WHERE p.id IN(:productIds)")
    List<Product> getByProductIds(@Param("productIds") Set<Long> productIds);
}
