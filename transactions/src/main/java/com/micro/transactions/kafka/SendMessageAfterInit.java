package com.micro.transactions.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SendMessageAfterInit implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(SendMessageAfterInit.class);

    private final KafkaService kafkaService;

    public SendMessageAfterInit(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> strings = readDataFile();
        strings.removeAll(Collections.singleton(null));
        strings.forEach(kafkaService::sendMessage);
    }

    private List<String> readDataFile() {
        BufferedReader reader;
        List<String> data = new ArrayList<>();
        try {
            File file = ResourceUtils.getFile("classpath:neo4jcommands.txt");
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            data.add(line);
            while (line != null) {
                line = reader.readLine();
                data.add(line);
                logger.info(line);
            }

            reader.close();
        } catch (IOException e) {
            logger.error("Error while reading data.sql");
        }
        return data;
    }
}