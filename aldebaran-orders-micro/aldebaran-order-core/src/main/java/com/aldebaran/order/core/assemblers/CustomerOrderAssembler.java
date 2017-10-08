package com.aldebaran.order.core.assemblers;

import com.aldebaran.data.domain.Price;
import com.aldebaran.order.core.entities.CustomerOrder;
import com.aldebaran.order.core.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class CustomerOrderAssembler extends AbstractOrikaAssembler  {

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

    public CustomerOrderResponse toCustomerOrderResponse(CustomerOrder customerOrder,
                                                         List<CustomerOrderProductsMap> customerOrderProductsMaps) {

        CustomerOrderResponse response = new CustomerOrderResponse();
        response.setOrderId(customerOrder.getId());
        response.setTimestamps(
                mapperFacade.map(customerOrder.getTimestamps(), TimestampsModel.class));
        response.setPriceModel(
                mapperFacade.map(customerOrder.getPriceSum(), PriceModel.class));
        response.setProducts(
                mapperFacade.mapAsList(customerOrderProductsMaps,
                                       CustomerOrderProductModel.class));
        return response;
    }

    public List<CustomerOrderResponse> toCustomerOrderResponse(
                List<CustomerOrder> customerOrders,
                Map<Long, List<CustomerOrderProductsMap>> customerOrderMap) {

        List<CustomerOrderResponse> response = new ArrayList<>();
        for(CustomerOrder customerOrder: customerOrders) {
            response.add(toCustomerOrderResponse(customerOrder,
                                                 customerOrderMap.get(customerOrder.getId())));
        }
        return response;
    }

    @Override
    public void register(MapperFactory factory) {
        factory
            .classMap(CustomerOrderProductsMap.class, CustomerOrderProductModel.class)
            .customize(new CustomMapper<CustomerOrderProductsMap, CustomerOrderProductModel>() {
                @Override
                public void mapAtoB(CustomerOrderProductsMap customerOrderProductsMap,
                                    CustomerOrderProductModel customerOrderProductModel,
                                    MappingContext context) {
                    Price price = (Price) customerOrderProductsMap.getMap().get("productPrice");

                    customerOrderProductModel.setPrice(new PriceModel(price.getPreTax(),
                                                                      price.getAfterTax()));
                }
            })
            .field("map['productId']", "productId")
            .field("map['productName']", "name")
            .field("map['productQuantity']", "quantity")
            .byDefault()
            .register();
    }
}
