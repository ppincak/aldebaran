package com.aldebaran.order.core;

import com.aldebaran.rest.error.event.ErrorEvent;


public enum CustomerOrderErrorEvents implements ErrorEvent {

    CUSTOMER_EMAIL_TAKEN(1, "customer.email.taken"),
    CUSTOMER_HAS_ORDERS(2, "customer.has.orders"),
    EMPTY_CUSTOMER_ORDER(3, "empty.customer.order"),
    PRODUCT_NAME_TAKEN(4, "product.name.taken"),
    PRODUCT_CODE_TAKEN(5, "product.code.taken"),
    PRODUCT_IMAGE_LINK_EXISTS(6, "product.image.link.exists");

    private final static int SUB_CODE = 4;

    private final int errorCode;
    private final int errorSubCode;
    private final String errorMessageKey;

    CustomerOrderErrorEvents(int errorCode, String errorMessageKey) {
        this.errorCode = errorCode;
        this.errorSubCode = SUB_CODE;
        this.errorMessageKey = errorMessageKey;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public int getErrorSubCode() {
        return errorSubCode;
    }

    @Override
    public String getErrorMessageKey() {
        return errorMessageKey;
    }
}
