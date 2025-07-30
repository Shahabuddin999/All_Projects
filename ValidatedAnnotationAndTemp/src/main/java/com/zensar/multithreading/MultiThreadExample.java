package com.zensar.multithreading;
import java.util.concurrent.*;

public class MultiThreadExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
        	try {
                Thread.sleep(5000);
                
            } catch (InterruptedException e) {}
        	return "Task 1 running on thread: " + Thread.currentThread().getName();
           
        }, executor);

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
        	try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {}
        	return "Task 2 running on thread: " + Thread.currentThread().getName();
        }, executor);

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
        	try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
        	return "Task 3 running on thread: " + Thread.currentThread().getName();
        }, executor);

        task1.thenAccept(result -> {
            System.out.println("Async Result: " + result);
        });
        task2.thenAccept(result -> {
            System.out.println("Async Result: " + result);
        });
        task3.thenAccept(result -> {
            System.out.println("Async Result: " + result);
        });
       // CompletableFuture.allOf(task1, task2, task3).join();
        for (int i = 0; i < 15; i++) {
            System.out.println("Main thread still working: " + i);
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        executor.shutdown();
        
    }
}
