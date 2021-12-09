package com.tarya.bank.security;

import com.tarya.bank.dbentity.User;
import com.tarya.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email : " + email));

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id : " + id));

        return UserPrincipal.create(user);
    }
}
