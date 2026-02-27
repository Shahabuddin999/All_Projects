package com.zensar.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Temp24 {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("Raj", "Stream", "API", "Development");
		Optional<String> max = words.stream().max((str1,str2)->str1.length()-str2.length()); 
		System.out.println(max.get());
		List<String> word = Arrays.asList(null, "Stream", "API", "Development");
		word.stream().forEach(System.out::println);
		
		List<Integer> collect = Stream.of(90,20).sorted().collect(Collectors.toList());
		System.out.println(collect);
	}
}
