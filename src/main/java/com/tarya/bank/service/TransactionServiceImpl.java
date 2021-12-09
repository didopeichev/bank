package com.tarya.bank.service;

import com.tarya.bank.exception.BankException;
import com.tarya.bank.dbentity.Account;
import com.tarya.bank.dbentity.Transaction;
import com.tarya.bank.dbentity.User;
import com.tarya.bank.model.ApiError;
import com.tarya.bank.model.TransactionRequest;
import com.tarya.bank.repository.AccountRepository;
import com.tarya.bank.repository.TransactionRepository;
import com.tarya.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tarya.bank.model.ApiError.CodeEnum.INSUFFICIENT_FUNDS;
import static com.tarya.bank.service.JwtService.getAuthenticationToken;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void makeTransaction(TransactionRequest request) {
        Optional<Account> account = accountRepository.findById(request.getAccountId());
        Account validAccount = new Account();
        if (account.isPresent()) {
            validAccount = account.get();
        }
        Optional<User> user = userRepository.findById(getAuthenticationToken().getId());

        checkUserAccountId(user, request);

        switch(request.getType()) {
            case DEPOSIT:
                depositToAccount(validAccount, request.getAmount());
                break;
            case WITHDRAW:
                withdrawFromAccount(validAccount, request.getAmount());
                break;
            default:

        }

        accountRepository.save(validAccount);

        Transaction transaction = new Transaction()
                .transactionType(request.getType())
                .dateTime(LocalDateTime.now())
                .amount(request.getAmount())
                .account(validAccount);

        transactionRepository.save(transaction);
    }

    private void withdrawFromAccount(Account validAccount, BigDecimal amount) {
        BigDecimal amountAfterWithdraw = validAccount.getMoneyStock().subtract(amount);
        if (amountAfterWithdraw.compareTo(BigDecimal.ZERO) < 0) {
            throw BankException.newBankException(INSUFFICIENT_FUNDS, "Withdraw amount bigger than current funds");
        }
        validAccount.moneyStock(amountAfterWithdraw);
    }

    private void depositToAccount(Account validAccount, BigDecimal amount) {
        BigDecimal amountAfterDeposit = validAccount.getMoneyStock().add(amount);
        validAccount.moneyStock(amountAfterDeposit);
    }


    private void checkUserAccountId(Optional<User> user, TransactionRequest request) {
        List<Long> accountIdsForUser = user.get().getAccounts().stream()
                .map(Account::getId)
                .collect(Collectors.toList());

       if (!accountIdsForUser.contains(request.getAccountId())) {
           throw BankException.newBankException(ApiError.CodeEnum.INCORRECT_VALIDATION,
                   "User does not own account with such id");
       }
    }
}
