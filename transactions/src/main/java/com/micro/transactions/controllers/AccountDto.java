package com.micro.transactions.controllers;

public class AccountDto {

    public Long id;
    public int accountNumber;
    public String label = "ACCOUNT";

    public AccountDto(Long id, int accountNumber) {
        this.id = id;
        this.accountNumber = accountNumber;
    }
}
