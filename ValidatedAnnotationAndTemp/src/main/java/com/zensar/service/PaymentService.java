package com.zensar.service;

import org.springframework.stereotype.Service;

import com.zensar.nextdto.Payment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {

    public CompletableFuture<List<Payment>> getPayments(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return List.of(
                new Payment("p1", "o1", "SUCCESS"),
                new Payment("p2", "o2", "PENDING")
            );
        });
    }

    private void simulateDelay() {
        try { Thread.sleep(700); } catch (InterruptedException e) { }
    }
}
