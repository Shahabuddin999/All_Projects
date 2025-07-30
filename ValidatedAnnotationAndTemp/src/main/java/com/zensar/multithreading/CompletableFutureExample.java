package com.zensar.multithreading;
import java.util.concurrent.*;

public class CompletableFutureExample {

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {}
            return "Price from Amazon";
        });

        // Do other work while task runs
        System.out.println("Main thread doing other work...");

        // Define what to do when result is ready (async)
        future.thenAccept(result -> {
            System.out.println("Received async result: " + result);
        });

        CompletableFuture.supplyAsync(() -> "Hello")
        .thenApply(s -> s + " World")
        .thenAccept(System.out::println);
        
        // Simulate ongoing work in main thread
        for (int i = 0; i < 5; i++) {
            System.out.println("Main thread working: " + i);
            Thread.sleep(1000);
        }

        

        // Block main thread at end so app doesn’t exit early
        Thread.sleep(5000); // Only needed in this demo setup
    }
}
