package com.tarya.bank.controller;

import com.tarya.bank.model.TransactionRequest;
import com.tarya.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateAccountStock(@Valid @RequestBody TransactionRequest request) {
        transactionService.makeTransaction(request);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
