package com.saga.sagadesign.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saga.sagadesign.DTO.InventoryFailedEvent;
import com.saga.sagadesign.DTO.InventorySuccessEvent;
import com.saga.sagadesign.DTO.OrderCreatedEvent;
import com.saga.sagadesign.DTO.PaymentFailedEvent;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestParam Long orderId) {
        kafkaTemplate.send("order-created", new OrderCreatedEvent(orderId));
        System.out.println("Order created: " + orderId);
        return ResponseEntity.ok("Order created");
    }

    @KafkaListener(topics = "inventory-success")
    public void onInventorySuccess(InventorySuccessEvent event) {
        System.out.println("Order " + event.orderId() + " completed successfully.");
    }

    @KafkaListener(topics = {"payment-failed", "inventory-failed"})
    public void onFailure(ConsumerRecord<String, Object> record) { // <--- CHANGE IS HERE!
        Object event = record.value(); // Extract the deserialized value from the record

        Long id = null;

        if (event instanceof PaymentFailedEvent p) {
            id = p.orderId();
            System.out.println("Payment failed for Order ID: " + id + ". Initiating rollback.");
        } else if (event instanceof InventoryFailedEvent i) {
            id = i.orderId();
            System.out.println("Inventory failed for Order ID: " + id + ". Initiating rollback.");
            System.out.println("Refund issued for order: " + id);
        } else {
            System.err.println("Received an unexpected event type on failure topics: " + event.getClass().getName());
            System.err.println("Raw ConsumerRecord: " + record); // Log raw record for debugging
            return;
        }

        if (id != null) {
            System.out.println("Order " + id + " failed and rolled back (generic message).");
        }
    }
}
