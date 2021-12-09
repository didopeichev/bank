package com.tarya.bank.service;

import com.tarya.bank.model.CreateAccountRequest;

public interface BankService {
    void createAccount(CreateAccountRequest request);
}
