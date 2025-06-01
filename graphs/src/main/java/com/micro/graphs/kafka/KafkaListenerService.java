package com.micro.graphs.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @KafkaListener(topics = "transaction-topic", groupId = "graphs-group")
    public void listenTransactions(String message) {
        System.out.println("Received Message: " + message);
    }
}
