package com.tarya.bank.model;

import com.tarya.bank.dbentity.TransactionType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransactionRequest {

    @NotNull
    private TransactionType type;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long accountId;

    public TransactionRequest() {
    }

    public TransactionRequest(BigDecimal amount, Long accountId) {
        this.type = TransactionType.DEPOSIT;
        this.amount = amount;
        this.accountId = accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionRequest setType(TransactionType type) {
        this.type = type;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionRequest setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public TransactionRequest setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }
}
