package com.aldebaran.api.services;

import com.aldebaran.omanager.core.CustomerOrderErrorCodes;
import com.aldebaran.omanager.core.assemblers.ProductAssembler;
import com.aldebaran.omanager.core.entities.Product;
import com.aldebaran.omanager.core.model.ProductRequest;
import com.aldebaran.omanager.core.model.ProductResponse;
import com.aldebaran.omanager.core.model.update.ProductUpdateRequest;
import com.aldebaran.omanager.core.repositories.ProductRepository;
import com.aldebaran.rest.error.codes.ApplicationException;
import com.aldebaran.rest.search.PaginationRequest;
import com.aldebaran.rest.search.PaginationResponse;
import com.aldebaran.rest.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class ProductServiceImpl extends AbstractApiService<ProductRepository, Product> implements ProductService {

    @Autowired
    private ProductAssembler productAssembler;

    @Autowired
    private Validator validator;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        checkProductName(productRequest.getName());
        Product product = productAssembler.toEntity(productRequest);
        repository.save(product);
        return productAssembler.toResponse(product);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        return productAssembler.toResponse(getById(productId));
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        Product product = getById(productId);
        ProductRequest productRequest = productAssembler.merge(product, productUpdateRequest);
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(productRequest);
        if(violations.isEmpty() == false) {
            throw new ConstraintViolationException(violations);
        }
        productAssembler.merge(product, productRequest);
        repository.save(product);
        return productAssembler.toResponse(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        repository.findOne(productId);
    }

    @Override
    public List<Product> getProducts(Set<Long> productIds) {
        return repository.getByProductIds(productIds);
    }

    @Override
    public PaginationResponse<ProductResponse> getProducts(SearchRequest searchRequest, PaginationRequest paginationRequest) {
        return null;
    }

    private void checkProductName(String name) {
        Product product = repository.getByName(name);
        if(product != null) {
            throw new ApplicationException(CustomerOrderErrorCodes.PRODUCT_NAME_TOKEN);
        }
    }
}