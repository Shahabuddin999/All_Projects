package com.zensar.multithreading;
public class WithoutVolatileExample {
    private static boolean running = true;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread started...");
            while (running) {
            	//System.out.println("running inside while");
            }
            System.out.println("Thread ended.");
        });

        thread.start();

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        running = false; // Threads will not be able to see the updated value of this variable because its normal variable
        System.out.println("Main thread changed 'running' to false.");
    }
}
