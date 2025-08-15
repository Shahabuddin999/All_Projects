package com.zensar.temp;

import java.util.Arrays;
import java.util.List;

public class EvenAndOdd {

    private static void printEven(List<Integer> list) {
        list.stream()
            .filter(val -> val % 2 == 0)
            .forEach(val -> System.out.println("Even: " + val));
        System.out.println(Thread.currentThread().getName());
    }

    private static void printOdd(List<Integer> list) {
        list.stream()
            .filter(val -> val % 2 != 0)
            .forEach(val -> System.out.println("Odd: " + val));
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Thread evenThread = new Thread(() -> printEven(numbers), "Thread-Even");
        Thread oddThread  = new Thread(() -> printOdd(numbers), "Thread-Odd");

        evenThread.start();
        oddThread.start();
    }
}

// Same result is below code

//package com.zensar.temp;
//
//import java.util.Arrays;
//import java.util.List;
//
//class EvenAndOdd implements Runnable{
//	public List<Integer> list = Arrays.asList(4,5,7,10,11);
//	void even(List<Integer> list){
//		list.stream().filter(val->val%2==0).forEach(val->{
//			System.out.println("Even : "+val);
//		});
//	}
//	void odd(List<Integer> list){
//		list.stream().filter(val->val%2!=0).forEach(val->{
//			System.out.println("Odd : "+val);
//		});
//	}
//	@Override
//	public void run(){
//	List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//		if(Thread.currentThread().getName().equals("even"))
//			even(list);
//		else if(Thread.currentThread().getName().equals("odd"))
//			odd(list);
//	}
//	public static void main(String[] args) {
//		EvenAndOdd obj = new EvenAndOdd();
//		Thread even = new Thread(obj);
//		Thread odd = new Thread(obj);
//		even.setName("even");
//		odd.setName("odd");
//		even.start();
//		odd.start();
//	}
//}