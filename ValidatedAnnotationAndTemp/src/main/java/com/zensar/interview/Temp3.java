package com.zensar.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Temp3 {
	public static void main(String[] args) {
		String str="Welcome to java class";
		 Arrays.stream(str.split(" ")).forEach(data->{
			 System.out.println(new StringBuffer(data).reverse().toString());
		 });
		 
		 str = "java java is object oriented oriented";
		 Arrays.stream(str.split(" ")).distinct().forEach(System.out::println);
		 
		 str = "Shahabuddin";
		 Map<String, Long> collect = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		 System.out.println(collect);
		 
		 List<Integer> list = List.of(10,5,20,15);
		 List<Integer> collect2 = list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		 System.out.println(collect2);
		 System.out.println(list);
	}

}
