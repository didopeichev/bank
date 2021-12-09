package com.tarya.bank.controller;

import com.tarya.bank.model.CreateAccountRequest;
import com.tarya.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final BankService bankService;

    @Autowired
    public AccountController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping()
    public void createAccountForUser(@Valid @RequestBody CreateAccountRequest request) {
        bankService.createAccount(request);
    }

}
