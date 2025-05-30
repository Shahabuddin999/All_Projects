package com.zensar.temp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Conversion {

	public static Map<String, Integer> countStrings(List<String> inputList) {
		return inputList.stream().collect(Collectors.toMap(s -> s, String::length, (oldValue, newValue) -> oldValue));
	}

	public static void main(String[] args) {

		String name = "Shahabuddin Ansari Koraon";
		String array[] = name.split(" ");

		List<String> list = Arrays.asList(array);
//		list = Arrays.asList("aa","bb","cc");
		String[] newArr = list.toArray(new String[0]);

		char charArray[] = name.toCharArray();
		String val = new String(charArray);

		String result = String.join(" ", list);
		System.out.println(result);

		List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
		Map<String, Integer> fruits = countStrings(words);

		System.out.println(fruits);
		
		Integer[] intArry = {10,20,30,40,60,70,80,90,100,12,34,45,67,43,65,76,8};
		List<Integer> numbers = Arrays.asList(70,80,90,100,12,34,45,67);
		
		Arrays.sort(intArry,Collections.reverseOrder());
		Collections.sort(numbers,Collections.reverseOrder());
		
		Arrays.stream(intArry).forEach(value->{
			System.out.print(value+" ");
		});
		System.out.println("\n"+numbers);

	}

}
