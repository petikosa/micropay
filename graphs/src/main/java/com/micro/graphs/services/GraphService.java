package com.micro.graphs.services;

import com.micro.graphs.controllers.Graph;
import com.micro.graphs.controllers.Node;
import com.micro.graphs.controllers.Relationship;
import com.micro.graphs.controllers.RelationshipLabel;
import com.micro.graphs.nodes.Account;
import com.micro.graphs.nodes.Transaction;
import com.micro.graphs.repositories.AccountRepository;
import com.micro.graphs.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public GraphService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account findByAccountNumber(int accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Graph findAllCustom() {
        return convertToGraph(accountRepository.findAll(), transactionRepository.findAll());
    }

    private Graph convertToGraph(List<Account> accounts, List<Transaction> transactions) {
        List<Node> nodes = new ArrayList<>();
        List<Relationship> relationships = new ArrayList<>();
        long i = 0;
        for (Account a : accounts) {
            nodes.add(new Node(a.getId(), a.getLabel()));
            relationships.add(new Relationship(100 - i++, a.getId(), a.getTransaction().getId(), RelationshipLabel.PERFORMS));
        }
        for (Transaction t : transactions) {
            nodes.add(new Node(t.getId(), t.getLabel()));
            relationships.add(new Relationship(100 - i++, t.getId(), t.getAccount().getId(), RelationshipLabel.BENEFITS_NO));
        }
        Graph graph = new Graph();
        graph.nodes = nodes;
        graph.relationships = relationships;
        return graph;
    }
}
