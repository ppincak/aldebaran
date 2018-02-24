package com.aldebaran.order.api.controllers;

import com.aldebaran.order.api.hal.CustomerHal;
import com.aldebaran.order.api.services.CustomerOrderService;
import com.aldebaran.order.api.services.CustomerService;
import com.aldebaran.order.core.model.*;
import com.aldebaran.order.core.model.update.CustomerOrderUpdateRequest;
import com.aldebaran.order.core.model.update.CustomerUpdateRequest;
import com.aldebaran.rest.interceptors.ApplyDeviceContext;
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
@ApplyDeviceContext
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "/customers",
     description = "Operations regarding customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @GET
    @ApiOperation(value = "Get customers by complex search criteria")
    public Response getCustomers(@BeanParam SearchRequest searchRequest,
                                 @BeanParam PaginationRequest paginationRequest) {
        return Response
                .ok(CustomerHal.addLinks(customerService.getCustomers(searchRequest, paginationRequest)))
                .build();
    }

    @POST
    @ApiOperation(value = "Create customer",
                  response = CustomerResponse.class)
    public Response createCustomer(@Valid CustomerRequest customerRequest) {
        return Response
                .status(Response.Status.CREATED)
                .entity(CustomerHal.addLinks(customerService.createCustomer(customerRequest)))
                .build();
    }

    @GET
    @Path("/{customerId}")
    @ApiOperation(value = "Find customer by id",
                  response = CustomerResponse.class)
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        return Response
                .ok(CustomerHal.addLinks(customerService.getCustomerById(customerId)))
                .build();
    }

    @PUT
    @Path("/{customerId}")
    @ApiOperation(value = "Update customer by",
                  response = CustomerResponse.class)
    public Response updateCustomer(@PathParam("customerId") Long customerId,
                                   CustomerUpdateRequest request) {
        return Response
                .ok(CustomerHal.addLinks(customerService.updateCustomer(customerId, request)))
                .build();
    }

    @DELETE
    @Path("/{customerId}")
    @ApiOperation(value = "Delete customer by id",
                  response = Void.class)
    public Response deleteCustomer(@PathParam("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
        return Response
                .noContent()
                .build();
    }

    @POST
    @Path("/{customerId}/photos/{photoId}")
    @ApiOperation(value = "Assign photo to customer",
                  response = CustomerResponse.class)
    public Response addCustomerPhoto(@PathParam("customerId") Long customerId,
                                     @PathParam("photoId") Long photoId) {
        return Response
                .ok(customerService.addCustomerPhoto(customerId, photoId))
                .build();
    }

    @GET
    @Path("/{customerId}/orders")
    @ApiOperation(value = "Get customer orders by complex search criteria")
    public Response getCustomerOrders(@PathParam("customerId") Long customerId,
                                      @BeanParam SearchRequest searchRequest,
                                      @BeanParam PaginationRequest paginationRequest) {
        return Response
                .ok(customerOrderService.getCustomerOrders(customerId, searchRequest, paginationRequest))
                .build();
    }

    @POST
    @Path("/{customerId}/orders")
    @ApiOperation(value = "Create customer order",
                  response = CustomerOrderResponse.class)
    public Response createCustomerOrder(@PathParam("customerId") Long customerId,
                                        @Valid CustomerOrderRequest customerOrderRequest) {
        return Response
                .status(Response.Status.CREATED)
                .entity(customerOrderService.createCustomerOrder(customerId, customerOrderRequest))
                .build();
    }

    @PUT
    @Path("/{customerId}/orders/{orderId}")
    @ApiOperation(value = "Update customer order by customer and order id",
                  response = CustomerOrderResponse.class)
    public Response updateCustomerOrder(@PathParam("orderId") Long customerOrderId,
                                        @Valid CustomerOrderUpdateRequest customerOrderRequest) {
        return Response
                .status(Response.Status.CREATED)
                .entity(customerOrderService.updateCustomerOrder(customerOrderId, customerOrderRequest))
                .build();
    }

    @DELETE
    @Path("/{customerId}/orders/{orderId}")
    @ApiOperation(value = "Delete customer order by customer and order id",
                  response = Void.class)
    public Response deleteOrder(@PathParam("orderId") Long customerOrderId) {
        customerOrderService.deleteCustomerOder(customerOrderId);
        return Response
                .noContent()
                .build();
    }
}