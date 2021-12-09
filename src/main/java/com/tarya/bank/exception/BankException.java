package com.tarya.bank.exception;

import com.tarya.bank.model.ApiError;
import com.tarya.bank.model.BankErrorWrapper;

import java.time.LocalDateTime;
import java.util.UUID;

public class BankException extends RuntimeException {
    private final BankErrorWrapper bankError;

    BankException() {
        bankError = new BankErrorWrapper()
                .id(UUID.randomUUID().toString())
                .timestamp(LocalDateTime.now());
    }

    public BankErrorWrapper getBankError() {
        return bankError;
    }

    public static BankException newBankException(ApiError.CodeEnum code, String message) {
        BankException bankException = new BankException();
        ApiError apiError = new ApiError().code(code).detail(message);
        bankException.getBankError().error(apiError);
        return bankException;
    }

    public static BankException newBankAuthException() {
        BankException bankException = new BankException();
        ApiError apiError = new ApiError().code(ApiError.CodeEnum.UNAUTHORIZED).detail("User must login again");
        bankException.getBankError().error(apiError);
        return bankException;
    }
}
