package com.zensar.interview;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Temp23 {
	public static void main(String[] args) {
		int num = 125690;
		String number2=String.valueOf(num);
		int a=IntStream.rangeClosed(0,9).filter(s->!number2.contains(String.valueOf(s))).findFirst().orElse(-1);
		System.out.println(a);
		System.out.println("------------------------");
		
		IntStream.rangeClosed(0, 9).forEach(n->{
			if(!String.valueOf(num).contains(String.valueOf(n))) {
				System.out.println(n);
			}
		});
		
		int number = 152689;

		int min = String.valueOf(number).chars().map(ch -> ch - '0').min().getAsInt();
		int max = String.valueOf(number).chars().map(ch -> ch - '0').max().getAsInt();
		System.out.println("------------------------");
		IntStream.rangeClosed(min, max).filter(val->!String.valueOf(number).contains(String.valueOf(val))).forEach(System.out::println);
	}
}
