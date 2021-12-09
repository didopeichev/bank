package com.tarya.bank.service;

import com.tarya.bank.converter.EntityConverter;
import com.tarya.bank.dbentity.User;
import com.tarya.bank.exception.BankException;
import com.tarya.bank.model.*;
import com.tarya.bank.repository.UserRepository;
import com.tarya.bank.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.tarya.bank.model.ApiError.CodeEnum.INCORRECT_VALIDATION;
import static com.tarya.bank.service.JwtService.getAuthenticationToken;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @Override
    public ResponseEntity<Void> registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw BankException.newBankException(INCORRECT_VALIDATION, "User with same email exists!");
        }
        User user = new User(signUpRequest.getName(), signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public UserDto getUserInfo() {
        Optional<User> user = userRepository.findById(getAuthenticationToken().getId());
        return user.map(EntityConverter::convertToUserDto).orElse(null);
    }
}
