package com.aldebaran.order.core.model;

import com.aldebaran.data.utils.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;


public class PriceModel {

    @JsonProperty
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal preTax;

    @JsonProperty
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal afterTax;

    @JsonProperty
    private String currency;

    public PriceModel() {
    }

    public PriceModel(BigDecimal preTax, BigDecimal afterTax) {
        this.preTax = preTax;
        this.afterTax = afterTax;
    }

    public BigDecimal getPreTax() {
        return preTax;
    }

    public void setPreTax(BigDecimal preTax) {
        this.preTax = preTax;
    }

    public BigDecimal getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(BigDecimal afterTax) {
        this.afterTax = afterTax;
    }
}