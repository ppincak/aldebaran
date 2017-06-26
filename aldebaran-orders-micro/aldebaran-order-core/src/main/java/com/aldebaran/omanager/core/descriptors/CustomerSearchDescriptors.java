package com.aldebaran.omanager.core.descriptors;

import com.aldebaran.rest.search.AbstractSearchDescriptors;
import org.springframework.stereotype.Component;


@Component
public final class CustomerSearchDescriptors extends AbstractSearchDescriptors {

    protected void init() {
        addDescriptor("id", Long.class);
        addDescriptor("firstName", String.class);
        addDescriptor("lastName", String.class);
        addDescriptor("email", String.class);
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
