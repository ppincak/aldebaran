package com.aldebaran.omanager.core.assemblers;

import com.aldebaran.omanager.core.entities.Product;
import com.aldebaran.omanager.core.model.PriceModel;
import com.aldebaran.omanager.core.model.ProductRequest;
import com.aldebaran.omanager.core.model.ProductResponse;
import com.aldebaran.omanager.core.model.update.ProductUpdateRequest;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


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

    public List<ProductResponse> toResponseList(List<Product> products) {
        return mapperFacade.mapAsList(products, ProductResponse.class);
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
            .customize(new CustomMapper<Product, ProductUpdateRequest>() {
                @Override
                public void mapAtoB(Product product, ProductUpdateRequest updateRequest, MappingContext context) {
                    updateRequest.setProperty("preTax", product.getPrice().getPreTax());
                    updateRequest.setProperty("afterTax", product.getPrice().getAfterTax());
                }
            })
            .register();

        registerMap(factory.classMap(ProductRequest.class, ProductUpdateRequest.class))
            .mapNulls(false)
            .customize(new CustomMapper<ProductRequest, ProductUpdateRequest>() {
                @Override
                public void mapBtoA(ProductUpdateRequest updateRequest, ProductRequest productRequest, MappingContext context) {
                    productRequest
                            .setPrice(new PriceModel((BigDecimal) updateRequest.getMap().get("preTax"),
                                                     (BigDecimal) updateRequest.getMap().get("afterTax")));
                }
            })
            .register();
    }

    private <A, B> ClassMapBuilder<A, B> registerMap(ClassMapBuilder<A, B> builder) {
        builder
            .field("name", "map['name']")
            .field("description", "map['description']")
            .field("code", "map['code']");
        return builder;
    }
}
