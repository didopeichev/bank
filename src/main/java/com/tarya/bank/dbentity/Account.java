package com.tarya.bank.dbentity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String number;

    @NotNull
    private BigDecimal moneyStock;

    @NotNull
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("accounts")
    private User user;

    @NotNull
    private LocalDateTime createdDate;

    public Account() {
    }

    public Account(String number, String currency, User user) {
        this.number = number;
        this.currency = currency;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.moneyStock = BigDecimal.valueOf(0);
    }

    public Long getId() {
        return id;
    }

    public Account id(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Account number(String number) {
        this.number = number;
        return this;
    }

    public BigDecimal getMoneyStock() {
        return moneyStock;
    }

    public Account moneyStock(BigDecimal moneyStock) {
        this.moneyStock = moneyStock;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Account currency(String currency) {
        this.currency = currency;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Account user(User user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Account createdDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}
