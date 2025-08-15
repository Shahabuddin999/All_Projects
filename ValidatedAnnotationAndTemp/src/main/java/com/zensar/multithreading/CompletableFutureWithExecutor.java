package com.zensar.multithreading;
import java.util.concurrent.*;

import com.zensar.temp.TransactionPlus;

public class CompletableFutureWithExecutor {

	static ExecutorService executor = Executors.newFixedThreadPool(2);
	static CompletableFuture<TransactionPlus> getTransaction(String str) {
		 CompletableFuture<TransactionPlus> future = CompletableFuture.supplyAsync(() -> {
	            try {
	                Thread.sleep(5000);
	            } catch (InterruptedException e) {}
	            TransactionPlus obj = new  TransactionPlus();
	            obj.setAddress("Alld"); obj.setAmount(100); obj.setCategory("Prime: "+str);
	            return obj;
	        }, executor);
		return future; 
	}
	
    public static void main(String[] args) throws Exception {
       

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
            return "Price from Flipkart";
        }, executor); // 👈 custom executor used here

        System.out.println("Main thread is doing something else...");

        future.thenAccept(result -> {
            System.out.println("Async Result: " + result);
        });
        
        getTransaction("Shahabuddin").thenAccept(result->{
        	System.out.println("Async Result: "+result);
        });

        // Simulate more main thread work
        for (int i = 0; i < 15; i++) {
            System.out.println("Main thread still working: " + i);
            Thread.sleep(500);
        }

        // Cleanup // stop taking new tasks
        executor.shutdown();
    }
}
