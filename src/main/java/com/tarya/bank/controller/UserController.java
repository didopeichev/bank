package com.tarya.bank.controller;

import com.tarya.bank.model.LoginRequest;
import com.tarya.bank.model.SignUpRequest;
import com.tarya.bank.model.UserDto;
import com.tarya.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }

    @GetMapping("/info")
    public ResponseEntity<UserDto> getUserInfo() {
        return ResponseEntity.ok(userService.getUserInfo());
    }
}
