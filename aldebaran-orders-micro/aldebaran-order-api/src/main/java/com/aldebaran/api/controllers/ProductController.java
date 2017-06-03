package com.aldebaran.api.controllers;

import com.aldebaran.api.services.ProductService;
import com.aldebaran.omanager.core.model.CustomerResponse;
import com.aldebaran.omanager.core.model.ProductRequest;
import com.aldebaran.omanager.core.model.ProductResponse;
import com.aldebaran.omanager.core.model.update.ProductUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Component
@Path("/products")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "/products",
     description = "Product operations")
public class ProductController {

    @Autowired
    private ProductService productService;

    @POST
    @ApiOperation(value = "Create product",
                  response = ProductResponse.class)
    public Response createProduct(@Valid ProductRequest productRequest) {
        return Response
                .ok(productService.createProduct(productRequest))
                .build();
    }

    @GET
    @Path("/{productId}")
    @ApiOperation(value = "Find product by id",
                  response = ProductResponse.class)
    public Response getProduct(@PathParam("productId") Long productId) {
        return Response
                .ok(productService.getProduct(productId))
                .build();
    }

    @PUT
    @Path("/{productId}")
    @ApiOperation(value = "Update product by id",
                  response = ProductResponse.class)
    public Response updateProduct(@PathParam("productId") Long productId,
                                  ProductUpdateRequest productRequest) {
        return Response
                .ok(productService.updateProduct(productId, productRequest))
                .build();
    }

    @DELETE
    @Path("/{productId}")
    @ApiOperation(value = "Delete product by id",
                  response = Void.class)
    public Response deleteProduct(@PathParam("productId") Long productId) {
        productService.deleteProduct(productId);
        return Response
                .noContent()
                .build();
    }
}