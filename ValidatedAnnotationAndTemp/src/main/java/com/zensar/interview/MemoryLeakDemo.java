package com.zensar.interview;
import java.util.*;

public class MemoryLeakDemo {
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
    	Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("User Thread: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });
        t.start();

        System.out.println("Main thread finished");
        
        
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running...");
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });

        daemon.setDaemon(true); // इसे daemon बनाना जरूरी है
        daemon.start();
    }
}
