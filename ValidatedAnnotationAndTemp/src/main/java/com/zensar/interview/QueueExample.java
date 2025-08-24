package com.zensar.interview;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueExample {
    public static void main(String[] args) {
        // FIFO Queue
        Queue<Integer> queue = new LinkedList<>();
        queue.add(30);
        queue.add(10);
        queue.add(20);

        // PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(30);
        pq.add(10);
        pq.add(20);

        // Stack
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(10);
        stack.push(20);

        System.out.println("Queue Removal (FIFO):");
        while (!queue.isEmpty()) System.out.print(queue.poll() + " ");

        System.out.println("\nPriorityQueue Removal (Priority smallest first):");
        while (!pq.isEmpty()) System.out.print(pq.poll() + " ");

        System.out.println("\nStack Removal (LIFO):");
        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
        
        
        System.out.println("\n Blocking Queue ");
     // BlockingQueue with capacity 5
        BlockingQueue<Integer> bQueue = new ArrayBlockingQueue<>(5);

        // Producer thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Produced: " + i);
                    bQueue.put(i);  // will block if queue is full
                    Thread.sleep(500); // simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer item = bQueue.take();  // will block if queue is empty
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1000); // simulate processing time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start both threads
        producer.start();
        consumer.start();
    }
}
