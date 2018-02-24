package com.aldebaran.order.api.hal;

import com.aldebaran.order.api.controllers.CustomerController;
import com.aldebaran.order.core.model.CustomerResponse;
import com.aldebaran.rest.hal.HalPagination;
import com.aldebaran.rest.search.PaginationResponse;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;


public class CustomerHal {

    public static CustomerResponse addLinks(CustomerResponse customerResponse) {
        customerResponse
                .add(JaxRsLinkBuilder
                        .linkTo(CustomerController.class)
                        .slash(customerResponse.getCustomerId())
                        .withSelfRel());

        customerResponse
                .add(JaxRsLinkBuilder
                        .linkTo(CustomerController.class)
                        .slash(customerResponse.getCustomerId())
                        .slash("orders")
                        .withRel("orders"));

        return customerResponse;
    }

    public static PaginationResponse<CustomerResponse> addLinks(
            PaginationResponse<CustomerResponse> paginationResponse) {

        for(CustomerResponse customerResponse: paginationResponse.getData()) {
            addLinks(customerResponse);
        }

        return HalPagination.toPagination("/customer", paginationResponse);
    }
}