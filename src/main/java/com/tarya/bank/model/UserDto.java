package com.tarya.bank.model;

import java.util.Set;

public class UserDto {
    private String name;
    private String email;
    private Set<AccountDto> accounts;

    public UserDto() {
    }

    public UserDto(String name, String email, Set<AccountDto> accounts) {
        this.name = name;
        this.email = email;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public UserDto name(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto email(String email) {
        this.email = email;
        return this;
    }

    public Set<AccountDto> getAccounts() {
        return accounts;
    }

    public UserDto accounts(Set<AccountDto> accounts) {
        this.accounts = accounts;
        return this;
    }
}
