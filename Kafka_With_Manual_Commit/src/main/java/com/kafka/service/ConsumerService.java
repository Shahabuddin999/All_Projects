package com.kafka.service;

import javax.management.RuntimeErrorException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "test-group", containerFactory = "kafkaListenerContainerFactory" )
    public void consume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        System.out.println("✅ Received: " + record.value());

        // Processing logic yahan aayega
        try {
            System.out.println("✅ Received: " + record.value());
            Thread.sleep(1000); // simulate processing

            if(record.value().contains("error")) // force error
            {
            	throw new RuntimeException("Error occured");
            }

            // Manual commit agar sab sahi chala
            acknowledgment.acknowledge();
            System.out.println("💾 Offset committed for message: " + record.value());

        } catch (Exception e) {
            System.err.println("❌ Error processing: " + record.value() + " | " + e.getMessage());
            // yahan commit mat karo
        }
    }
}
