package com.micro.graphs.repositories;

import com.micro.graphs.nodes.Account;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface AccountRepository extends Neo4jRepository<Account, Long> {

    Account findByAccountNumber(int accountNumber);

    @Query("match (n) return n")
    List<Object> findAllCustom();
}
