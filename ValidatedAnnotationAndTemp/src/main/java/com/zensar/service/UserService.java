package com.zensar.service;

import org.springframework.stereotype.Service;

import com.zensar.nextdto.User;

import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    public CompletableFuture<User> getUser(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return new User(userId, "Shahab", "shahab@example.com");
        });
    }

    private void simulateDelay() {
        try { Thread.sleep(500); } catch (InterruptedException e) { }
    }
}
