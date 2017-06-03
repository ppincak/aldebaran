package com.aldebaran.api.services;

import com.aldebaran.omanager.core.entities.Product;
import com.aldebaran.omanager.core.model.ProductRequest;
import com.aldebaran.omanager.core.model.ProductResponse;
import com.aldebaran.omanager.core.model.update.ProductUpdateRequest;

import java.util.List;
import java.util.Set;


public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProduct(Long productId);

    ProductResponse updateProduct(Long productId, ProductUpdateRequest productRequest);

    void deleteProduct(Long productId);

    List<Product> getProducts(Set<Long> productIds);
}