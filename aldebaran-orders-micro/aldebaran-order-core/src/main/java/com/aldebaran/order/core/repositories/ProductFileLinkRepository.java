package com.aldebaran.order.core.repositories;

import com.aldebaran.order.core.entities.ProductFileLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductFileLinkRepository extends JpaRepository<ProductFileLink, Long> {

    @Query("SELECT pf FROM ProductFileLink pf " +
           "WHERE pf.product.id=:productId " +
           "AND pf.fileLink.id=:fileLinkId")
    ProductFileLink getByProductAndFileLink(@Param("productId") Long productId,
                                            @Param("fileLinkId") Long fileLinkId);
}