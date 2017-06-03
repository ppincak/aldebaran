package com.aldebaran.api.controllers;

import com.aldebaran.omanager.core.model.update.CustomerOrderUpdateRequest;
import com.aldebaran.omanager.core.model.CustomerRequest;
import com.aldebaran.omanager.core.model.CustomerOrderRequest;
import com.aldebaran.omanager.core.model.CustomerResponse;
import com.aldebaran.omanager.core.model.update.CustomerUpdateRequest;
import com.aldebaran.api.services.CustomerService;
import com.aldebaran.rest.search.PaginationRequest;
import com.aldebaran.rest.search.SearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path("/customers")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "/customers",
     description = "Operations regarding customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GET
    public Response getCustomers(@BeanParam SearchRequest searchRequest,
                                 @BeanParam PaginationRequest paginationRequest) {
        return Response
                .ok(customerService.getCustomers(searchRequest, paginationRequest))
                .build();
    }

    @POST
    @ApiOperation(value = "Create customer",
                  response = CustomerResponse.class)
    public Response createCustomer(@Valid CustomerRequest customerRequest) {
        return Response
                .status(Response.Status.CREATED)
                .entity(customerService.createCustomer(customerRequest))
                .build();
    }

    @GET
    @Path("/{customerId}")
    @ApiOperation(value = "Find customer by id",
            response = CustomerResponse.class)
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        return Response
                .ok(customerService.getCustomerById(customerId))
                .build();
    }

    @PUT
    @Path("/{customerId}")
    @ApiOperation(value = "Update customer",
                  response = CustomerResponse.class)
    public Response updateCustomer(@PathParam("customerId") Long customerId,
                                   CustomerUpdateRequest request) {
        return Response
                .ok(customerService.updateCustomer(customerId, request))
                .build();
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
        return Response
                .noContent()
                .build();
    }

    @GET
    @Path("/{customerId}/orders")
    public Response getCustomerOrders(@PathParam("customerId") Long customerId) {
        return Response
                .ok(customerService.getCustomerOrders(customerId))
                .build();
    }

    @POST
    @Path("/{customerId}/orders")
    public Response createCustomerOrder(@PathParam("customerId") Long customerId,
                                        @Valid CustomerOrderRequest customerOrderRequest) {
        return Response
                .status(Response.Status.CREATED)
                .entity(customerService.createCustomerOrder(customerId, customerOrderRequest))
                .build();
    }

    @PUT
    @Path("/{customerId}/orders/{orderId}")
    public Response updateCustomerOrder(@PathParam("orderId") Long customerOrderId,
                                        @Valid CustomerOrderUpdateRequest customerOrderRequest) {
        return Response
                .status(Response.Status.CREATED)
                .entity(customerService.updateCustomerOrder(customerOrderId, customerOrderRequest))
                .build();
    }

    @DELETE
    @Path("/{customerId}/orders/{orderId}")
    public Response deleteOrder(@PathParam("orderId") Long customerOrderId) {
        customerService.deleteCustomerOder(customerOrderId);
        return Response
                .noContent()
                .build();
    }
}