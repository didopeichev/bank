package com.tarya.bank.service;

import com.tarya.bank.dbentity.Account;
import com.tarya.bank.dbentity.User;
import com.tarya.bank.model.CreateAccountRequest;
import com.tarya.bank.repository.AccountRepository;
import com.tarya.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.tarya.bank.service.JwtService.getAuthenticationToken;

@Service
public class BankServiceImpl implements BankService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public BankServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(CreateAccountRequest request) {
        Optional<User> user = userRepository.findById(getAuthenticationToken().getId());
        Account account = new Account()
                .user(user.get())
                .currency(request.getCurrency())
                .createdDate(LocalDateTime.now())
                .moneyStock(request.getInitialStock())
                .number(UUID.randomUUID().toString());

        accountRepository.save(account);
    }
}
