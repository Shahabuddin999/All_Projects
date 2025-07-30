package com.zensar.multithreading;
public class FixedDeadlock {
    public static void main(String[] args) {
        final String lock1 = "LOCK1";
        final String lock2 = "LOCK2";

        Runnable task = () -> {
        	System.out.println("Entered Thread: "+Thread.currentThread().getName());
            synchronized (lock1) {
            	try {
            		System.out.println(Thread.currentThread().getName() + ": Acquired lock1");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                synchronized (lock2) {
                	try {
                		System.out.println(Thread.currentThread().getName() + ": Acquired lock2");
    					Thread.sleep(1000);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
                    
                }
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
