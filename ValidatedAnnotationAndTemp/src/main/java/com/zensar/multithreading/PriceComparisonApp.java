package com.zensar.multithreading;
import java.util.concurrent.*;

public class PriceComparisonApp {

    // Simulated method to fetch price from a store
    public static Callable<String> fetchPrice1(String storeName, int delayInSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(delayInSeconds); // Simulate network/API delay
            return storeName + " price: ₹" + (1000 + (int)(Math.random() * 500));
        };
    }

    public static Callable<String> fetchPrice2(String storeName, int delayInSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(delayInSeconds); // Simulate network/API delay
            return storeName + " price: ₹" + (1000 + (int)(Math.random() * 500));
        };
    }

    public static Callable<String> fetchPrice3(String storeName, int delayInSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(delayInSeconds); // Simulate network/API delay
            return storeName + " price: ₹" + (1000 + (int)(Math.random() * 500));
        };
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Creating FutureTasks for different stores
        FutureTask<String> amazonTask = new FutureTask<>(fetchPrice1("Amazon", 2));
        FutureTask<String> flipkartTask = new FutureTask<>(fetchPrice2("Flipkart", 3));
        FutureTask<String> snapdealTask = new FutureTask<>(fetchPrice3("Snapdeal", 1));

        // Submit tasks to executor
        executor.submit(amazonTask);
        executor.submit(flipkartTask);
        executor.submit(snapdealTask);

        System.out.println("Fetching prices from online stores...");

        while (!amazonTask.isDone() || !flipkartTask.isDone() || !snapdealTask.isDone()) {
            System.out.println("Still waiting for results...");
            Thread.sleep(500);
        }

        // Get results (this will block until result is ready)
        System.out.println(amazonTask.get());
        System.out.println(flipkartTask.get());
        System.out.println(snapdealTask.get());
        dist();
        executor.shutdown();
    }
    
    static void dist() {
    	System.out.println("other method called");
    }
}
