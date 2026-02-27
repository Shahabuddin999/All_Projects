package com.zensar.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Temp16 {

	public static void main(String[] args) {
		List<String> list = List.of("ten","net","ate","eat","tea");
		Map<String, List<String>> collect2 = list.stream().map(str->{
			String collect = Arrays.stream(str.split("")).sorted((s1,s2)->s1.compareTo(s2)).collect(Collectors.joining());
			return collect;
		}).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.toList()));
		System.out.println(collect2);
		
		Map<String, Integer> collect = list.stream().collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(collect);
		
		Map<String, String> collect3 = list.stream().map(str->{
			return Arrays.stream(str.split("")).sorted((s1,s2)->s1.compareTo(s2)).collect(Collectors.joining());
			
		}).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.joining(" ")));
		System.out.println(collect3);
	}
}
