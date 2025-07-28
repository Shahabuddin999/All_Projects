package com.zensar.multithreading;

public class DeadLock {

	public static final Object lock1="Lock1";
	public static final Object lock2="Lock2";
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(()->{
			synchronized (lock1) {
				try {
					System.out.println("Thread1 holding lock1");
					Thread.sleep(100);
					synchronized (lock2) {
						System.out.println("Thread1 locked lock2");
					}
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		
		Thread t2 = new Thread(()->{
			synchronized (lock2) {
				try {
					System.out.println("Thread2 holding lock2");
					Thread.sleep(100);
					synchronized (lock1) {
						System.out.println("Thread1 locked lock1");
					}
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		
		t1.start();
		t2.start();
		
		/*
	    What is a Deadlock in Threads?
		A deadlock is a situation where two or more threads are blocked forever, each waiting for a resource (lock) that the other thread holds.
		In simple terms:
		Thread A waits for a lock held by Thread B, while Thread B waits for a lock held by Thread A — they never proceed.
		
		Real-World Analogy:
		Person A has Lock 1, and wants Lock 2
		Person B has Lock 2, and wants Lock 1
	 */
	}
}
