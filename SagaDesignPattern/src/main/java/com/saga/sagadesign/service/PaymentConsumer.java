package com.saga.sagadesign.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.saga.sagadesign.DTO.InventoryFailedEvent;
import com.saga.sagadesign.DTO.OrderCreatedEvent;
import com.saga.sagadesign.DTO.PaymentFailedEvent;
import com.saga.sagadesign.DTO.PaymentSuccessEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "order-created")
    public void processPayment(OrderCreatedEvent event) {
        if (event.orderId()%2 == 0) { // simulate random failure
            kafkaTemplate.send("payment-success", new PaymentSuccessEvent(event.orderId()));
            System.out.println("Payment success for order " + event.orderId());
        } else {
            kafkaTemplate.send("payment-failed", new PaymentFailedEvent(event.orderId()));
            System.out.println("Payment failed for order " + event.orderId());
        }
    }

	/*
	 * @KafkaListener(topics = "inventory-failed") public void
	 * handleRefund(InventoryFailedEvent event) {
	 * System.out.println("Refund issued for order: " + event.orderId()); }
	 */
}
