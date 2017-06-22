package com.aldebaran.omanager.core.descriptors;

import com.aldebaran.rest.search.AbstractSearchDescriptors;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public final class ProductSearchDescriptors extends AbstractSearchDescriptors {

    public void init() {
        addDescriptor("id", Long.class);
        addDescriptor("name", String.class);
        addDescriptor("code", String.class);
        addDescriptor("preTaxPrice", BigDecimal.class, "price.preTax");
        addDescriptor("afterTaxPrice", BigDecimal.class, "price.afterTax");
    }
}
