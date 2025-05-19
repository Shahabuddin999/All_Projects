package com.zensar;

import java.util.Arrays;
import java.util.List;

class EvenAndOdd implements Runnable{
	public List<Integer> list = Arrays.asList(4,5,7,10,11);
	void even(List<Integer> list){
		list.stream().filter(val->val%2==0).forEach(val->{
			System.out.println("Even : "+val);
		});
	}
	void odd(List<Integer> list){
		list.stream().filter(val->val%2!=0).forEach(val->{
			System.out.println("Odd : "+val);
		});
	}
	@Override
	public void run(){
	List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		if(Thread.currentThread().getName().equals("even"))
			even(list);
		else if(Thread.currentThread().getName().equals("odd"))
			odd(list);
	}
	public static void main(String[] args) {
		EvenAndOdd obj = new EvenAndOdd();
		Thread even = new Thread(obj);
		Thread odd = new Thread(obj);
		even.setName("even");
		odd.setName("odd");
		even.start();
		odd.start();
	}
}