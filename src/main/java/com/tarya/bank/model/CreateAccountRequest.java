package com.tarya.bank.model;

import java.math.BigDecimal;

public class CreateAccountRequest {
    private String currency;
    private BigDecimal initialStock;

    public CreateAccountRequest(String currency, BigDecimal initialStock) {
        this.currency = currency;
        this.initialStock = initialStock;
    }

    public String getCurrency() {
        return currency;
    }

    public CreateAccountRequest setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BigDecimal getInitialStock() {
        return initialStock;
    }

    public CreateAccountRequest setInitialStock(BigDecimal initialStock) {
        this.initialStock = initialStock;
        return this;
    }
}
