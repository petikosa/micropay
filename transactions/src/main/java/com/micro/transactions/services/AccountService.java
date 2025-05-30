package com.micro.transactions.services;

import com.micro.transactions.controllers.AccountDto;
import com.micro.transactions.entities.Account;
import com.micro.transactions.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> new AccountDto(account.getId(), account.getAccountNumber())).toList();
    }

}
