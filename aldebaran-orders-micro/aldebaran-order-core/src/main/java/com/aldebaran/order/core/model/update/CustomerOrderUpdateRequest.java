package com.aldebaran.order.core.model.update;

import com.aldebaran.order.core.UpdateMode;
import com.aldebaran.order.core.model.CustomerOrderRequest;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CustomerOrderUpdateRequest extends CustomerOrderRequest {

    @JsonProperty
    private UpdateMode updateMode;

    public UpdateMode getUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(UpdateMode updateMode) {
        this.updateMode = updateMode;
    }
}