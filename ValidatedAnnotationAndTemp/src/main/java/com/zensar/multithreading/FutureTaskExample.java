package com.zensar.multithreading;

import java.util.concurrent.*;

public class FutureTaskExample {
    public static void main(String[] args) throws Exception {
        Callable<String> callableTask1 = () -> {
            Thread.sleep(2000); // Simulate long task
            return "Result from Callable";
        };

        FutureTask<String> futureTask1 = new FutureTask<>(callableTask1);
        FutureTask<String> futureTask2 = new FutureTask<>(() -> { 
        											return "Shahabuddin Ansari"; 
        										});

        //new Thread(futureTask2).start(); futureTask2.get();
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        executor.submit(futureTask1);
        executor.submit(futureTask2);

        System.out.println("Both tasks submitted, doing other work...");

        // Delay to simulate other work
        Thread.sleep(500);

        // Now fetch results — could be in any order
        System.out.println("Future 1: " + futureTask1.get());
        System.out.println("Future 2: " + futureTask2.get());
        
        

        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(1000);
            return 42;
        });
        System.out.println(future.get()); // blocks
   

        executor.shutdown();
    }
}
