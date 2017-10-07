package com.aldebaran.order.api.services;

import com.aldebaran.order.core.CustomerOrderErrorEvents;
import com.aldebaran.order.core.assemblers.ProductAssembler;
import com.aldebaran.order.core.descriptors.ProductSearchDescriptors;
import com.aldebaran.order.core.entities.FileLink;
import com.aldebaran.order.core.entities.Product;
import com.aldebaran.order.core.entities.ProductFileLink;
import com.aldebaran.order.core.model.ProductRequest;
import com.aldebaran.order.core.model.ProductResponse;
import com.aldebaran.order.core.model.update.ProductUpdateRequest;
import com.aldebaran.order.core.repositories.FileLinkRepository;
import com.aldebaran.order.core.repositories.ProductFileLinkRepository;
import com.aldebaran.order.core.repositories.ProductRepository;
import com.aldebaran.order.core.repositories.SearchCriteriaSpecification;
import com.aldebaran.rest.error.GeneralErrorEvents;
import com.aldebaran.rest.error.event.ApplicationException;
import com.aldebaran.rest.search.PaginationRequest;
import com.aldebaran.rest.search.PaginationResponse;
import com.aldebaran.rest.search.SearchCriterion;
import com.aldebaran.rest.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class ProductServiceImpl extends AbstractApiService<ProductRepository, Product> implements ProductService {

    @Autowired
    private ProductAssembler productAssembler;

    @Autowired
    private ProductSearchDescriptors productSearchDescriptors;

    @Autowired
    private FileLinkRepository fileLinkRepository;

    @Autowired
    private ProductFileLinkRepository productFileLinkRepository;

    @Autowired
    private Validator validator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        checkName(productRequest.getName());
        checkCode(productRequest.getCode());

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
    public PaginationResponse<ProductResponse> getProducts(SearchRequest searchRequest,
                                                           PaginationRequest paginationRequest) {
        PageRequest pageRequest =
                assemblePageRequest(paginationRequest, productSearchDescriptors.getOrderDescriptors());

        Set<SearchCriterion> criteria =
                searchRequest.toSearchCriteria(productSearchDescriptors.getSearchDescriptors());

        Page<Product> page;
        if(criteria.isEmpty()) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(SearchCriteriaSpecification.buildWithAnd(criteria), pageRequest);
        }

        return PaginationResponse
                .data(productAssembler.toResponseList(page.getContent()))
                .totalElements(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public void addImage(Long productId, Long imageId) {
        ProductFileLink productFileLink =
                productFileLinkRepository.getByProductAndFileLink(productId, imageId);

        if(productFileLink != null) {
            throw new ApplicationException(CustomerOrderErrorEvents.PRODUCT_IMAGE_LINK_EXISTS);
        }
        Product product = repository.findOne(productId);
        if(product == null) {
            throw new ApplicationException(GeneralErrorEvents.RESOURCE_NOT_FOUND);
        }
        FileLink fileLink = fileLinkRepository.findOne(imageId);
        if(fileLink == null) {
            throw new ApplicationException(GeneralErrorEvents.RESOURCE_NOT_FOUND);
        }
        productFileLink = new ProductFileLink();
        productFileLink.setProduct(product);
        productFileLink.setFileLink(fileLink);
        productFileLinkRepository.save(productFileLink);
    }

    @Override
    public void removeImage(Long productId, Long imageId) {
        ProductFileLink productFileLink =
                productFileLinkRepository.getByProductAndFileLink(productId, imageId);

        if(productFileLink == null) {
            throw new ApplicationException(GeneralErrorEvents.RESOURCE_NOT_FOUND);
        }
        productFileLinkRepository.delete(productFileLink);
    }

    private void checkName(String name) {
        if(repository.countByName(name) > 0) {
            throw new ApplicationException(CustomerOrderErrorEvents.PRODUCT_NAME_TAKEN);
        }
    }

    private void checkCode(String code) {
        if(repository.countByCode(code) > 0) {
            throw new ApplicationException(CustomerOrderErrorEvents.PRODUCT_CODE_TAKEN);
        }
    }
}