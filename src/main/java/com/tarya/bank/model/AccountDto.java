package com.tarya.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDto {

    private Long id;
    private String uniqueNumber;
    private BigDecimal moneyStock;
    private String currency;
    private LocalDateTime createdDate;

    public AccountDto() {
    }

    public AccountDto(Long id, String uniqueNumber, BigDecimal moneyStock, String currency, LocalDateTime createdDate) {
        this.id = id;
        this.uniqueNumber = uniqueNumber;
        this.moneyStock = moneyStock;
        this.currency = currency;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public AccountDto id(Long id) {
        this.id = id;
        return this;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public AccountDto uniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
        return this;
    }

    public BigDecimal getMoneyStock() {
        return moneyStock;
    }

    public AccountDto moneyStock(BigDecimal moneyStock) {
        this.moneyStock = moneyStock;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountDto currency(String currency) {
        this.currency = currency;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public AccountDto createdDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
