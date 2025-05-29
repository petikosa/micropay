package com.micro.transactions.controllers;

import java.time.ZonedDateTime;

public class TransactionDto {

    public Long id;
    public int amount;
    public ZonedDateTime date;
    public String label = "TRANSACTION";

    public TransactionDto(Long id, int amount, ZonedDateTime date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }
}
