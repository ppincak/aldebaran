package com.aldebaran.order.api.services;

import com.aldebaran.order.api.hal.CustomerHal;
import com.aldebaran.order.core.CustomerOrderErrorEvents;
import com.aldebaran.order.core.assemblers.CustomerAssembler;
import com.aldebaran.order.core.descriptors.CustomerSearchDescriptors;
import com.aldebaran.order.core.entities.Customer;
import com.aldebaran.order.core.entities.FileLink;
import com.aldebaran.order.core.model.CustomerRequest;
import com.aldebaran.order.core.model.CustomerResponse;
import com.aldebaran.order.core.model.update.CustomerUpdateRequest;
import com.aldebaran.order.core.repositories.CustomerRepository;
import com.aldebaran.order.core.repositories.SearchCriteriaSpecification;
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
import java.util.Set;


@Service
@Transactional
public class CustomerServiceImpl
        extends AbstractApiService<CustomerRepository, Customer> implements CustomerService {

    @Autowired
    private CustomerAssembler customerAssembler;

    @Autowired
    private CustomerSearchDescriptors searchDescriptors;

    @Autowired
    private FileService fileService;

    @Autowired
    private Validator validator;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        checkCustomerEmail(null, customerRequest.getEmail());
        Customer customer = customerAssembler.toEntity(customerRequest);
        repository.save(customer);
        return customerAssembler.toResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerById(Long customerId) {
        return customerAssembler.toResponse(getById(customerId));
    }

    @Override
    public CustomerResponse updateCustomer(Long customerId, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = getById(customerId);
        CustomerRequest request = customerAssembler.merge(customer, customerUpdateRequest);
        Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(request);
        if(violations.isEmpty() == false) {
            throw new ConstraintViolationException(violations);
        }
        checkCustomerEmail(customerId, request.getEmail());

        customerAssembler.merge(customer, request);
        repository.save(customer);
        return customerAssembler.toResponse(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Long ordersCount = repository.ordersCount(customerId);
        if(ordersCount > 0) {
            throw new ApplicationException(CustomerOrderErrorEvents.CUSTOMER_HAS_ORDERS);
        }
        repository.delete(getById(customerId));
    }

    @Override
    public CustomerResponse addCustomerPhoto(Long customerId, Long photoId) {
        Customer customer = getById(customerId);
        FileLink fileLink = customer.getFileLink();
        if(fileLink == null || fileLink.getId().equals(photoId) == false) {
            fileLink = fileService.getFileLink(photoId);
            customer.setFileLink(fileLink);
            repository.save(customer);
        }
        return customerAssembler.toResponse(customer);
    }

    @Override
    public PaginationResponse<CustomerResponse> getCustomers(SearchRequest searchRequest,
                                                             PaginationRequest paginationRequest) {

        PageRequest pageRequest =
                assemblePageRequest(paginationRequest, searchDescriptors.getOrderDescriptors());

        Set<SearchCriterion> criteria =
                searchRequest.toSearchCriteria(searchDescriptors.getSearchDescriptors());

        Page<Customer> page;
        if(criteria.isEmpty()) {
            page = repository.findAll(pageRequest);
        } else {
            page = repository.findAll(SearchCriteriaSpecification.buildWithAnd(criteria), pageRequest);
        }

        return PaginationResponse
                .data(customerAssembler.toResponseList(page.getContent()))
                .totalElements(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .pagination(paginationRequest)
                .build();
    }

    private void checkCustomerEmail(Long customerId, String email) {
        Customer customer = repository.getByEmail(email);
        if(customerId != null && customer.getId().equals(customerId)) {
            return;
        }
        if(customer != null ) {
            throw new ApplicationException(CustomerOrderErrorEvents.CUSTOMER_EMAIL_TAKEN);
        }
    }
}