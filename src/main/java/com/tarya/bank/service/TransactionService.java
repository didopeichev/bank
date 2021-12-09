package com.tarya.bank.service;

import com.tarya.bank.model.TransactionRequest;

public interface TransactionService {

    void makeTransaction(TransactionRequest request);
}
