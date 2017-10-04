package com.aldebaran.order.core.descriptors;

import com.aldebaran.rest.search.AbstractSearchDescriptors;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public final class ProductSearchDescriptors extends AbstractSearchDescriptors {

    protected void init() {
        addDescriptor("id", Long.class);
        addDescriptor("name", String.class);
        addDescriptor("code", String.class);
        addDescriptor("preTaxPrice", BigDecimal.class, "price.preTax");
        addDescriptor("afterTaxPrice", BigDecimal.class, "price.afterTax");

        addOrderDescriptors("id", "name", "code");
        addOrderDescriptor("preTaxPrice","price.preTax");
        addOrderDescriptor("afterTaxPrice","price.afterTax");
        addOrderDescriptor("createdAt","timestamps.createdAt");
        addOrderDescriptor("updatedAt","timestamps.updatedAt");
    }
}
