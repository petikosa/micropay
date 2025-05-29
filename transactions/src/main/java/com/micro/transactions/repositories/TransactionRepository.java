package com.micro.transactions.repositories;

import com.micro.transactions.entities.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {
}
