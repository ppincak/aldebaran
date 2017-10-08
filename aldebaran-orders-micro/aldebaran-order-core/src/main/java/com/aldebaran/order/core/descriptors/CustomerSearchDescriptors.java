package com.aldebaran.order.core.descriptors;

import com.aldebaran.rest.search.AbstractSearchDescriptors;
import com.aldebaran.rest.search.SearchOperator;
import org.springframework.stereotype.Component;


@Component
public final class CustomerSearchDescriptors extends AbstractSearchDescriptors {

    @Override
    protected void init() {
        addDescriptor("id", Long.class, SearchOperator.IN);
        addDescriptor("firstName", String.class, SearchOperator.IN);
        addDescriptor("lastName", String.class, SearchOperator.IN);
        addDescriptor("email", String.class, SearchOperator.IN);
        addDescriptor("phone", String.class);

        addOrderDescriptor("id");
        addOrderDescriptor("firstName");
        addOrderDescriptor("lastName");
        addOrderDescriptor("email");
        addOrderDescriptor("phone");
        addOrderDescriptor("createdAt","timestamps.createdAt");
        addOrderDescriptor("updatedAt","timestamps.updatedAt");
    }
}
