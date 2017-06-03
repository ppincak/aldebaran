package com.aldebaran.omanager.core.assemblers;

import com.aldebaran.omanager.core.entities.Customer;
import com.aldebaran.omanager.core.entities.Product;
import com.aldebaran.omanager.core.model.ProductRequest;
import com.aldebaran.omanager.core.model.ProductResponse;
import com.aldebaran.omanager.core.model.update.ProductUpdateRequest;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;


@Component
public class ProductAssembler extends AbstractOrikaAssembler {

    public Product toEntity(ProductRequest productRequest) {
        return mapperFacade.map(productRequest, Product.class);
    }

    public ProductResponse toResponse(Product product) {
        return mapperFacade.map(product, ProductResponse.class);
    }

    public void merge(Product product, ProductRequest productRequest) {
        mapperFacade.map(productRequest, product);
    }

    public ProductRequest merge(Product product, ProductUpdateRequest updateRequest) {
        ProductUpdateRequest tempUpdateRequest =
                mapperFacade.map(product, ProductUpdateRequest.class);
        tempUpdateRequest.getMap().putAll(updateRequest.getMap());
        return mapperFacade.map(tempUpdateRequest, ProductRequest.class);
    }

    @Override
    public void register(MapperFactory factory) {
        factory
            .classMap(ProductRequest.class, Product.class)
            .byDefault()
            .register();

        factory
            .classMap(Product.class, ProductResponse.class)
            .byDefault()
            .register();

        registerMap(factory.classMap(Product.class, ProductUpdateRequest.class))
            .mapNulls(false)
            .register();

        registerMap(factory.classMap(ProductRequest.class, ProductUpdateRequest.class))
            .mapNulls(false)
            .register();
    }

    private <A, B> ClassMapBuilder<A, B> registerMap(ClassMapBuilder<A, B> builder) {
        builder
            .field("name", "map['name']")
            .field("description", "map['description']")
            .field("price", "map['price']")
            .field("code", "map['code']");
        return builder;
    }
}
