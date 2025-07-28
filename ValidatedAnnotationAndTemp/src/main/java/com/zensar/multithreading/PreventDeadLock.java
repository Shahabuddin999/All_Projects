package com.zensar.multithreading;
import java.util.concurrent.locks.ReentrantLock;

public class PreventDeadLock {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " trying to acquire lock...");
            lock.lock(); // Lock acquired
            try {
                System.out.println(Thread.currentThread().getName() + " got the lock!");
                Thread.sleep(1000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // Always unlock in finally
                System.out.println(Thread.currentThread().getName() + " released the lock!");
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
