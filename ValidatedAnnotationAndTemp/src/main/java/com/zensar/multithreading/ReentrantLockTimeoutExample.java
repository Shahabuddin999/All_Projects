package com.zensar.multithreading;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTimeoutExample {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " trying to acquire lock...");

            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(threadName + " acquired the lock!");
                        Thread.sleep(3000); // Simulate work
                    } finally {
                        lock.unlock();
                        System.out.println(threadName + " released the lock.");
                    }
                } else {
                    System.out.println(threadName + " could not acquire lock within timeout.");
                }
            } catch (InterruptedException e) {
                System.out.println(threadName + " was interrupted.");
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        // Ensure t1 starts and holds the lock first
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        t2.start();
    }
}
