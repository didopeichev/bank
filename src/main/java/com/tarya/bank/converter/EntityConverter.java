package com.tarya.bank.converter;

import com.tarya.bank.dbentity.Account;
import com.tarya.bank.dbentity.User;
import com.tarya.bank.model.AccountDto;
import com.tarya.bank.model.UserDto;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityConverter {

    public static UserDto convertToUserDto(User user) {
        return new UserDto()
                .email(user.getEmail())
                .name(user.getName())
                .accounts(convertSetAccounts(user.getAccounts()));
    }

    public static AccountDto convertToAccountDto(Account account) {
        return new AccountDto()
                .id(account.getId())
                .uniqueNumber(account.getNumber())
                .moneyStock(account.getMoneyStock())
                .currency(account.getCurrency())
                .createdDate(account.getCreatedDate());
    }

    public static Set<AccountDto> convertSetAccounts(Set<Account> accounts) {
        Set<AccountDto> accountDtoSet;
        accountDtoSet = accounts.stream()
                .map(EntityConverter::convertToAccountDto)
                .collect(Collectors.toSet());
        return accountDtoSet;
    }
}
