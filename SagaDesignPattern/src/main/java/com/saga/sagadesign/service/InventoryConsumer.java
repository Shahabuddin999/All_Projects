package com.saga.sagadesign.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.saga.sagadesign.DTO.InventoryFailedEvent;
import com.saga.sagadesign.DTO.InventorySuccessEvent;
import com.saga.sagadesign.DTO.PaymentSuccessEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "payment-success")
    public void processInventory(PaymentSuccessEvent event) {
        if (event.orderId() % 2 == 0) { // simulate failure
            kafkaTemplate.send("inventory-success", new InventorySuccessEvent(event.orderId()));
            System.out.println("Inventory updated for order " + event.orderId());
        } else {
        	 kafkaTemplate.send("inventory-failed", new InventoryFailedEvent(event.orderId()));
             System.out.println("Inventory failed for order " + event.orderId());
        }
    }
}
