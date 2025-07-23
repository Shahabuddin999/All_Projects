package com.zensar.multithreading;
import java.util.concurrent.*;

public class FutureTaskExample {
    public static void main(String[] args) throws Exception {
        // Step 1: Create a Callable task
        Callable<String> callableTask = () -> {
            Thread.sleep(2000); // Simulate some long computation
            return "Result from Callable";
        };

        // Step 2: Wrap it in a FutureTask
        FutureTask<String> futureTask = new FutureTask<>(callableTask);

        // Step 3: Use ExecutorService to execute the task
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(futureTask);  // or executor.execute(futureTask);

        System.out.println("Task submitted, doing other work...");

        // Step 4: Get the result
        String result = futureTask.get(); // This will block until the result is available

        System.out.println("Result: " + result);

        // Step 5: Shutdown the executor
        executor.shutdown();
    }
}
