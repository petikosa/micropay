package com.micro.graphs.repositories;

import com.micro.graphs.nodes.Transaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TransactionRepository extends Neo4jRepository<Transaction, Long> {
}
