package com.aldebaran.omanager.core;

import com.aldebaran.rest.error.event.ErrorEvent;


public enum CustomerOrderErrorEvents implements ErrorEvent {

    CUSTOMER_EMAIL_TAKEN(1, "customer.email.taken"),
    EMPTY_CUSTOMER_ORDER(2, "empty.customer.order"),
    PRODUCT_NAME_TOKEN(3, "product.mail.taken"),
    CUSTOMER_HAS_ORDERS(4, "customer.has.orders"),
    PRODUCT_FILE_LINK_EXISTS(5, "product.file.link.exists");

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
