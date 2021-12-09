package com.tarya.bank.service;

import com.tarya.bank.model.LoginRequest;
import com.tarya.bank.model.SignUpRequest;
import com.tarya.bank.model.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignUpRequest signUpRequest);

    UserDto getUserInfo();
}
