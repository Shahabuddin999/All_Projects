package com.kafka.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-group" /* groupId = "#{T(java.util.UUID).randomUUID().toString()}" */)
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
