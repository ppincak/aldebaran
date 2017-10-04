package com.aldebaran.order.api.services;

import com.aldebaran.order.core.model.*;
import com.aldebaran.order.core.model.update.CustomerOrderUpdateRequest;
import com.aldebaran.order.core.model.update.CustomerUpdateRequest;
import com.aldebaran.rest.search.PaginationRequest;
import com.aldebaran.rest.search.PaginationResponse;
import com.aldebaran.rest.search.SearchRequest;


public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    PaginationResponse<CustomerResponse> getCustomers(SearchRequest searchRequest,
                                                      PaginationRequest paginationRequest);

    CustomerResponse getCustomerById(Long customerId);

    CustomerResponse updateCustomer(Long customerId, CustomerUpdateRequest customerUpdateRequest);

    void deleteCustomer(Long customerId);

    CustomerResponse addCustomerPhoto(Long customerId, Long photoId);

    CustomerOrdersResponse getCustomerOrders(Long customerId);

    CustomerOrderResponse createCustomerOrder(Long customerId,
                                              CustomerOrderRequest customerOrderRequest);

    CustomerOrderResponse updateCustomerOrder(Long customerOrderId,
                                              CustomerOrderUpdateRequest customerOrderRequest);

    void deleteCustomerOder(Long customerOrderId);
}