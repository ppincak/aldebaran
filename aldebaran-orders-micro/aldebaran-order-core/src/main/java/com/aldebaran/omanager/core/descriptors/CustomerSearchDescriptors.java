package com.aldebaran.omanager.core.descriptors;

import org.springframework.stereotype.Component;


@Component
public final class CustomerSearchDescriptors extends AbstractSearchDescriptors {

    protected void init() {
        addDescriptor("id", Long.class,"id");
        addDescriptor("firstName", String.class,"firstName");
        addDescriptor("lastName", String.class, "lastName");
        addDescriptor("email", String.class, "email");

        addOrderDescriptor("id");
        addOrderDescriptor("firstName");
        addOrderDescriptor("lastName");
        addOrderDescriptor("email");
        addOrderDescriptor("phone");
        addOrderDescriptor("createdAt",
                           "timestamps.createdAt");
        addOrderDescriptor("updatedAt",
                           "timestamps.updatedAt");
    }
}
