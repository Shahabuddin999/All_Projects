package com.zensar.temp;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {

	
	public static void disp() throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Callable<String> callable = ()->{
			Thread.sleep(5000);
			return "Shahabuddin";
		};
		Future<String> future = executorService.submit(callable);
		int check=1;
		while(!future.isDone()) {
			Thread.sleep(200);
			check++;
			if(check>5) {
				future.cancel(true);
				System.out.println("Task is in progress "+future.isDone());
			}
		}
		System.out.println("Done : "+future.isDone());
		System.out.println("Cancelled : "+future.isDone());
		if(!future.isCancelled()) {
			System.out.println(future.get());
		}else {
			System.out.println("Future is cancelled");
		}
	}
	public static void main(String[] args) throws Exception {
		disp();
	}

}
