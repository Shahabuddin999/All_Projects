package com.zensar.java11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Temp3 {
	public static void main(String[] args) {
		String[] list = {"Java","Streams","Java","Java8","Functional","7Java"};
		Arrays.stream(list).map(str->str.contains("Java")? str.replace("Java","Python"): str).forEach(System.out::println);
		Map<String, Long> collect = Arrays.stream(list).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(collect);
		
		BinaryOperator<Integer> obj = (a,b)->a+b;
		Integer apply = obj.apply(10, 20);
		System.out.println(apply);
		System.out.println(Arrays.stream(list).max((str1,str2)->str1.length()-str2.length()).get());
		Integer[] add = {10,20,30,40,50};
		Integer collect2 = Arrays.stream(add).collect(Collectors.summingInt(a->a));
		System.out.println(collect2);
		
		System.out.println("sum: "+Arrays.stream(add).reduce(0,(a,b)->a+b));
		System.out.println("sum1: "+Arrays.stream(add).mapToInt(v->v).sum());
		
		System.out.println(IntStream.of(10,20,30,40).mapToObj(a->a).collect(Collectors.summingInt(a->a)));
		
		System.out.println("=============");
		Stream.of("a", "b", "c")
	      .peek(System.out::print)
	      .map(String::toUpperCase)
	      .collect(Collectors.toList());
		
		System.out.println("=============");
		Optional<Integer> secondMax = List.of(10,10,15,14,20)
				.stream()
			    .distinct()
			    .sorted(Comparator.reverseOrder())
			    .skip(1)
			    .findFirst();
		System.out.println(secondMax.get());
		
		List.of(10,10,15,14,20)
		.stream()
	    .distinct()
	    .sorted(Comparator.reverseOrder())
	    .skip(1)
	    .limit(2)
	    .forEach(System.out::println);
	}
}