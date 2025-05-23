package com.micro.transactions.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private int amount;
    
    private ZonedDateTime date;

    private String label = "TRANSACTION";

    @OneToMany
    private Set<Account> account;

    public String getLabel() {
        return label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
    }
}
