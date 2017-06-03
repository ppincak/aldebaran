package com.aldebaran.data.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;


@Embeddable
public class Price {

    @Column(name = "pre_tax")
    private BigDecimal preTax;

    @Column(name = "after_tax")
    private BigDecimal afterTax;

 /*   @Column(name = "currency")
    private String currency;
*/
    public Price() {
        preTax = BigDecimal.ZERO;
        afterTax = BigDecimal.ZERO;
    }

    public Price(Price price) {
        preTax = price.getPreTax();
        afterTax = price.getAfterTax();
    }

    public Price(BigDecimal preTax, BigDecimal afterTax) {
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

    /*public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }*/
}