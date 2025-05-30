package com.micro.transactions.services;

import com.micro.transactions.controllers.TransactionDto;
import com.micro.transactions.entities.Transaction;
import com.micro.transactions.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {


    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transaction ->
                        new TransactionDto(transaction.getId(), transaction.getAmount(), transaction.getDate())).toList();
    }
}
