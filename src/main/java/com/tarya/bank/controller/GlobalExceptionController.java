package com.tarya.bank.controller;

import com.tarya.bank.exception.BankException;
import com.tarya.bank.model.BankErrorWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.tarya.bank.model.ApiError.CodeEnum.INCORRECT_VALIDATION;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler(BankException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BankErrorWrapper handleError(BankException bankException) {
        log.error("Bank Error", bankException);
        return bankException.getBankError();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMediaTypeNotSupportedException.class,
            IllegalArgumentException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BankErrorWrapper handleValidationError(Exception exception) {
        log.error("Validation Error", exception);
        return BankException.newBankException(INCORRECT_VALIDATION, exception.getMessage()).getBankError();
    }
}
