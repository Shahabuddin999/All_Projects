package com.zensar.temp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimeChecker {
	public static boolean isPrime(int n) {
		System.out.println(Math.sqrt(n));
		if (n <= 1)
			return false; // 0 and 1 are not prime
		if (n == 2)
			return true; // 2 is prime
		if (n % 2 == 0)
			return false; // even numbers greater than 2 are not prime

		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			System.out.println(i);
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static boolean checkPrime(int number) {
		return number > 2 && IntStream.rangeClosed(2, (int) Math.sqrt(number)).allMatch(n -> {
			boolean check = number % n != 0;
			System.out.println("Math.sqrt(number) n : " + n + ", number : " + number);
			return check;

		});
	}

	public static void main(String[] args) {

		int number = 97;
		if (isPrime(number)) {
			System.out.println(number + " is a prime number.");
		} else {
			System.out.println(number + " is not a prime number.");
		}

		System.out.println("Prime numbers from 1 to 100:");

		IntStream.rangeClosed(2, 20).filter(val -> PrimeChecker.checkPrime(val)).forEach(System.out::println);

		
		
		List<Integer> list1 = Arrays.asList(5, 12, 7);
		List<Integer> list2 = Arrays.asList(20, 3, 8);
		List<Integer> list3 = Arrays.asList(15, 2, 30);
		List<List<Integer>> list4 = Arrays.asList(list1, list2, list3);

		// Merge and find 2nd highest
		Optional<Integer> secondHighest = Arrays.asList(list1, list2, list3) // Stream.of(list1, list2, list3)
				.stream()
				.flatMap(x -> x.stream()) // Merge all lists
				.distinct() // Optional: remove duplicates
				.sorted(Comparator.reverseOrder()) // Sort in descending order
				.skip(1) // Skip the highest
				.findFirst(); // Get the second highest
		System.out.println("2nd highest element: " + secondHighest.get());

		
		
		List<Integer> secondHighestValue = Stream.of(list1, list2, list3) // Stream.of(list1, list2, list3)
				.flatMap(x -> x.stream()) // Merge all lists
				.distinct() // Optional: remove duplicates
				.sorted((v1, v2) -> v2 - v1)
				.collect(Collectors.toList()); // Sort in descending order
		System.out.println("2nd highest element: " + secondHighestValue.get(1));

		
		
		String[] rawArray = { "apple", null, "banana", "apple", "kiwi", null, "orangee", "bananaaaa" };
		// Remove nulls, remove duplicates, sort alphabetically
		List<String> cleanedSortedList = Arrays.stream(rawArray)
				.filter(val -> Objects.nonNull(val)) // Remove nulls
				.distinct() // Remove duplicates
				.sorted((v1, v2) -> v1.length() - v2.length()) // Sort alphabetically you can use
				.collect(Collectors.toList()); // .sorted(Comparator.naturalOrder())
		System.out.println("Sorted (alphabetically): " + cleanedSortedList);
		
		Arrays.stream(new int[] {10,3,5,7,12,18}).filter(val->val%2==0).forEach(val->System.out.println("Even :"+val));
		Arrays.stream(new int[] {10,3,5,7,12,18}).filter(val->val%2!=0).forEach(val->System.out.println("Odd :"+val));
		
		List<Customer> customer = Customer.getCustomers();
		Optional<Customer> cus = customer.stream().max((obj1,obj2)->obj1.getName().length()-obj2.getName().length());
		System.out.println(cus);
		
		//how to remove duplicates
		List<String> list = Arrays.asList("Hello","world","Java","world","program");
		list = list.stream().distinct().collect(Collectors.toList());
		System.out.println(list);
		
		//frequency of each number using java 8
		int[] intVal = {1,2,3,3,4,4,4,5,5,2}; 
		Map<Integer,Integer> map = Arrays.stream(intVal)
				.boxed()  // .boxed() converts primitive int to Integer objects, .collect() can't collect primitive value so used .boxed()
				.collect(Collectors.toMap(Function.identity(),init->1,Math::addExact));
		System.out.println(map);
		
		String [] str={"Hello", "World", "Everyone"};
		List<Integer> len = Arrays.asList(str).stream().map(val->val.length()).collect(Collectors.toList());
		System.out.println(len);
	}

}
