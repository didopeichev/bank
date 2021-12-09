package com.tarya.bank.model;

import java.time.LocalDateTime;

public class BankErrorWrapper {
    private String id;

    private LocalDateTime timestamp;

    private ApiError error;

    public String getId() {
        return id;
    }

    public BankErrorWrapper id(String id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BankErrorWrapper timestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ApiError getError() {
        return error;
    }

    public BankErrorWrapper error(ApiError error) {
        this.error = error;
        return this;
    }
}
