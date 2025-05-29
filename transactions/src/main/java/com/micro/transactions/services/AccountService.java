package com.micro.transactions.services;

import com.micro.transactions.controllers.AccountDto;
import com.micro.transactions.entities.Account;
import com.micro.transactions.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Flux<AccountDto> getAllAccounts() {
        Flux<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();
        return accounts.map(account -> new AccountDto(account.getId(), account.getAccountNumber()));
    }

}
