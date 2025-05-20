package com.zensar.temp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Temp2 {

	public static void main(String[] args) {
		
//		LongStream doubleStream = IntStream.range(1, 10).filter(i -> i % 2 != 0).asLongStream();
//		System.out.println(doubleStream.toArray()[1]);
//
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//		int sum = numbers.stream().mapToInt(Integer::intValue).sum();
//		System.out.println(sum); // Output: 15
		
//		 System.out.println("version: " + SpringVersion.getVersion());
//		 System.out.println("version: " + SpringBootVersion.getVersion());

		List<Customer> list = Customer.getCustomers();
		
		List<List<String>> inMultipleListUsingWithoutFlatMap = list.stream().map(customer->customer.getPhoneNumbers()).collect(Collectors.toList());
		System.out.println("Using map() : " + inMultipleListUsingWithoutFlatMap);
		
		List<String> inSingleListUsingFlatMap = list.stream().flatMap(customer->customer.getPhoneNumbers().stream()).collect(Collectors.toList());
		System.out.println("Using flatMap() : " + inSingleListUsingFlatMap);
	}

}
