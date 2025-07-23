package com.zensar.multithreading;

import java.util.concurrent.*;

public class FutureTaskNonBlockingExample {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);

		FutureTask<String> task = new FutureTask<>(() -> {
			// Simulate some delay
			Thread.sleep(3000);
			return "Hello FutureTask";
		}) {
			@Override
			protected void done() {
				try {
					// This get() won't block because task is already done
					System.out.println("Result: " + get());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		executor.submit(task);

		System.out.println("Main thread continues without blocking");

		// Do some other work
		for (int i = 0; i < 5; i++) {
			System.out.println("Main thread working: " + i);
			try {
				Thread.sleep(500); // Simulating some main thread work
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();
	}
}
