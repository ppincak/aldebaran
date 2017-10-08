package com.aldebaran.order.api.services;

import com.aldebaran.order.core.model.CustomerOrderRequest;
import com.aldebaran.order.core.model.CustomerOrderResponse;
import com.aldebaran.order.core.model.update.CustomerOrderUpdateRequest;
import com.aldebaran.rest.search.PaginationRequest;
import com.aldebaran.rest.search.PaginationResponse;
import com.aldebaran.rest.search.SearchRequest;


public interface CustomerOrderService {

    PaginationResponse<CustomerOrderResponse> getCustomerOrders(Long customerId,
                                                                SearchRequest searchRequest,
                                                                PaginationRequest paginationRequest);

    CustomerOrderResponse createCustomerOrder(Long customerId,
                                              CustomerOrderRequest customerOrderRequest);

    CustomerOrderResponse updateCustomerOrder(Long customerOrderId,
                                              CustomerOrderUpdateRequest customerOrderRequest);

    void deleteCustomerOder(Long customerOrderId);
}
