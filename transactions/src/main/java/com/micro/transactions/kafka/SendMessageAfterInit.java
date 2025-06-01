package com.micro.transactions.kafka;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class SendMessageAfterInit implements InitializingBean {

    private final KafkaService kafkaService;

    public SendMessageAfterInit(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        kafkaService.sendMessage("new mess");
    }
}