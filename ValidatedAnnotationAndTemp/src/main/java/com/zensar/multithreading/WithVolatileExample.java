package com.zensar.multithreading;
public class WithVolatileExample {
    private static volatile boolean running = true; // 👈 volatile added

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

        running = false; // 👈 change is now visible to other thread because of its volatile variable
        System.out.println("Main thread changed 'running' to false.");
    }
}
