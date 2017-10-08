package com.aldebaran.order.core.descriptors;

import com.aldebaran.rest.search.AbstractSearchDescriptors;
import com.aldebaran.rest.search.SearchOperator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class CustomerOrderSearchDescriptors extends AbstractSearchDescriptors {

    @Override
    protected void init() {
        addDescriptor("id", Long.class, SearchOperator.IN);
        addDescriptor("orderStatus", String.class);
        addDescriptor("preTaxSum", BigDecimal.class, "priceSum.preTax");
        addDescriptor("afterTaxSum", BigDecimal.class, "priceSum.afterTax");

        addOrderDescriptor("id");
        addOrderDescriptor("priceSum.beforeTax");
        addOrderDescriptor("priceSum.afterTax");
    }
}