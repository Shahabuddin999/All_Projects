package com.zensar.interview;
import java.util.*;

public class CompareDS {
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
    }
}
