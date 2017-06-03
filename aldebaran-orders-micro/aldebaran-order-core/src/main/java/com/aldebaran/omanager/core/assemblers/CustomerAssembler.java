package com.aldebaran.omanager.core.assemblers;

import com.aldebaran.omanager.core.entities.Customer;
import com.aldebaran.omanager.core.entities.CustomerOrder;
import com.aldebaran.omanager.core.entities.CustomerOrderProduct;
import com.aldebaran.omanager.core.model.*;
import com.aldebaran.omanager.core.model.update.CustomerUpdateRequest;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomerAssembler extends AbstractOrikaAssembler {

    @Autowired
    private ImageAssembler imageAssembler;

    public Customer toEntity(CustomerRequest request) {
        return mapperFacade.map(request, Customer.class);
    }

    public CustomerResponse toResponse(Customer customer) {
        return mapperFacade.map(customer, CustomerResponse.class);
    }

    public void merge(Customer customer, CustomerRequest customerRequest) {
        mapperFacade.map(customerRequest, customer);
    }

    public CustomerRequest merge(Customer customer, CustomerUpdateRequest customerUpdateRequest) {
        CustomerUpdateRequest tempMapReq = mapperFacade.map(customer, CustomerUpdateRequest.class);
        tempMapReq.getMap().putAll(customerUpdateRequest.getMap());
        return mapperFacade.map(tempMapReq, CustomerRequest.class);
    }

    public List<CustomerResponse> toResponseList(List<Customer> customers) {
        return mapperFacade.mapAsList(customers, CustomerResponse.class);
    }

    public CustomerOrderResponse toCustomerOrderResponse(CustomerOrder customerOrder) {
        CustomerOrderResponse response = new CustomerOrderResponse();
        response.setOrderId(customerOrder.getId());
        response.setTimestamps(
                mapperFacade.map(customerOrder.getTimestamps(), TimestampsModel.class));
        response.setPriceModel(
                mapperFacade.map(customerOrder.getPriceSum(), PriceModel.class));
        response.setProducts(
                mapperFacade.mapAsList(customerOrder.getOrderProducts(),
                                       CustomerOrderProductModel.class));
        return response;
    }

    public CustomerOrdersResponse toCustomerOrderResponse(List<CustomerOrder> customerOrders) {
        CustomerOrdersResponse response = new CustomerOrdersResponse();
        for(CustomerOrder customerOrder: customerOrders) {
            response.addOrder(toCustomerOrderResponse(customerOrder));
        }
        return response;
    }

    @Override
    public void register(MapperFactory factory) {
        factory
            .classMap(CustomerRequest.class, Customer.class)
            .byDefault()
            .register();

        factory
            .classMap(Customer.class, CustomerRequest.class)
            .byDefault()
            .register();

        factory
            .classMap(Customer.class, CustomerResponse.class)
            .byDefault()
            .customize(new CustomMapper<Customer, CustomerResponse>() {
                @Override
                public void mapAtoB(Customer customer,
                                    CustomerResponse customerResponse,
                                    MappingContext context) {

                    customerResponse.setImageUrl(
                            imageAssembler.assembleImageUrl(customer.getImage()));
                }
            })
            .register();

        registerMap(factory.classMap(Customer.class, CustomerUpdateRequest.class))
            .mapNulls(false)
            .register();

        registerMap(factory.classMap(CustomerRequest.class, CustomerUpdateRequest.class))
            .mapNulls(false)
            .register();

        factory
            .classMap(CustomerOrderProduct.class, CustomerOrderProductModel.class)
            .field("product.id", "productId")
            .field("product.name", "name")
            .byDefault()
            .register();
    }

    private <A, B>ClassMapBuilder<A, B> registerMap(ClassMapBuilder<A, B> builder) {
        builder
            .field("email", "map['email']")
            .field("firstName", "map['firstName']")
            .field("lastName", "map['lastName']")
            .field("phone", "map['phone']");

        return builder;
    }
}