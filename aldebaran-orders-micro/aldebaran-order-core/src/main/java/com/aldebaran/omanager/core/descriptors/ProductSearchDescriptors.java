package com.aldebaran.omanager.core.descriptors;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public final class ProductSearchDescriptors extends AbstractSearchDescriptors {

    public void init() {
        addDescriptor("id", Long.class, "id");
        addDescriptor("name", String.class, "name");
        addDescriptor("code", String.class, "code");
        addDescriptor("preTaxPrice", BigDecimal.class, "price.preTax");
        addDescriptor("afterTaxPrice", BigDecimal.class, "price.afterTax");
    }
}
