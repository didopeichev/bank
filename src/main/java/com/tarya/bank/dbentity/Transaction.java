package com.tarya.bank.dbentity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(Account account, TransactionType transactionType, BigDecimal amount) {
        this.account = account;
        this.transactionType = transactionType;
        this.dateTime = LocalDateTime.now();
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Transaction setId(Long id) {
        this.id = id;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Transaction account(Account account) {
        this.account = account;
        return this;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Transaction transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Transaction dateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
