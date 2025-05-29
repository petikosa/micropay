package com.micro.transactions.repositories;

import com.micro.transactions.entities.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<Account, Long> {
}
