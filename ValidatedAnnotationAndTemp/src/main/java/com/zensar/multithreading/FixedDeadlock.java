package com.zensar.multithreading;
public class FixedDeadlock {
    public static void main(String[] args) {
        final String lock1 = "LOCK1";
        final String lock2 = "LOCK2";

        Runnable task = () -> {
            synchronized (lock1) {
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + ": Acquired both locks!");
                }
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
