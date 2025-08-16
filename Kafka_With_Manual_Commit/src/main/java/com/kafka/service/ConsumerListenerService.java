package com.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class ConsumerListenerService {

    @KafkaListener(
            topics = "manual-topic",
            groupId = "manual-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(String message, Acknowledgment ack) {
       
            if (message.contains("fail")) {
                throw new RuntimeException("Forced error");
            }

            // ✅ Manual acknowledgment
            ack.acknowledge();
            System.out.println("Offset committed for: " + message);
    }
}
