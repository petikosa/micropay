package com.micro.graphs.kafka;

import org.neo4j.driver.Driver;
import org.neo4j.driver.QueryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaListenerService {
    Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);

    private final Driver neo4jDriver;

    public KafkaListenerService(Driver neo4jDriver) {
        this.neo4jDriver = neo4jDriver;
    }

    private final StringBuilder commands = new StringBuilder();

    @KafkaListener(topics = "transaction-topic", groupId = "graphs-group")
    public void listenTransactions(String message) {
        if (message.equals("eof")) {
            var result = neo4jDriver.executableQuery(commands.toString())
                    .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                    .execute();

            var summary = result.summary();
            logger.info("Created {} records", summary.counters().nodesCreated());
        } else {
            commands.append(message).append(" ");
            logger.info("Received Message: " + message);
        }
    }
}
