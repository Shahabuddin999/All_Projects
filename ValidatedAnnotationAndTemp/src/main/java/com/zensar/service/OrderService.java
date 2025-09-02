package com.zensar.service;

import org.springframework.stereotype.Service;

import com.zensar.nextdto.Order;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    public CompletableFuture<List<Order>> getOrders(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return List.of(
                new Order("o1", "Laptop", 60000.0),
                new Order("o2", "Mobile", 15000.0)
            );
        });
    }

    private void simulateDelay() {
        try { Thread.sleep(800); } catch (InterruptedException e) { }
    }
}
