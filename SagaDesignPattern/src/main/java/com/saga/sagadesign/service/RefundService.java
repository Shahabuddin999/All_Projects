package com.saga.sagadesign.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.saga.sagadesign.DTO.RefundCompletedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "refund-completed")
    public void processRefund(RefundCompletedEvent e) {
        System.out.println("Refund processed for order: " + e.orderId());
    }
}
