package com.tarya.bank.service;

import com.tarya.bank.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtService {

    private JwtService() {
    }

    static UserPrincipal getAuthenticationToken() {
        try {
            return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving principal from security context");
        }
    }
}
