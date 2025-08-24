package com.zensar.interview;

public class Temp18 implements Runnable{

	private final String name;
	private static Temp18 obj = null;
	private Temp18(String name){
		this.name = name;
	}
	public static synchronized Temp18 getInstance(String name) {
		if(obj == null)
			obj = new Temp18(name);
		return obj;
	}
	
	public static void main(String[] args) {
		Temp18 obj = Temp18.getInstance("Shahabuddin");
		obj = Temp18.getInstance("Ansari");
		Thread t1 = new Thread(obj,"T1");
		Thread t2 = new Thread(obj,"T2");
		Thread t3 = new Thread(obj,"T3");
		t1.start();
		t2.start();
		t3.start();
	}
	@Override
	public void run() {
		for(int i = 0; i<10; i++) {
			System.out.print(i+" "+Thread.currentThread().getName()+", ");
			System.out.println(this.name);
		}
		System.out.println();
	}
}
