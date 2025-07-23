package com.zensar.temp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Conversion {

		public static void main(String[] args) {

		String name = "Shahabuddin Ansari Koraon";
		String array[] = name.split(" ");

		List<String> list = Arrays.asList(array);
		list = List.of("vv","nn");
		list = List.of(array);
//		list = Arrays.asList("aa","bb","cc");
		String[] newArray = list.toArray(String[]::new);
		String[] newArr = list.stream().toArray(String[]::new); //🔹 More flexible — allows for transformation, filtering before conversion.

		char charArray[] = name.toCharArray();
		String val = new String(charArray);

		String result = String.join(" ", list);
		System.out.println("result : "+result);

		List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
		//Map<String, Integer> collect = words.stream().collect(Collectors.toMap(str1->str1, str->str.length(),(oldValue, newValue) -> oldValue));
		Map<String, Integer> collect = words.stream().distinct().collect(Collectors.toMap(str1->str1, str->str.length()));
		// Collectors has no any direct method which returns the length of string
		System.out.println("Fruits : "+collect);
		Integer[] intArry = {10,20,30,40,60,70,80,90,100,12,34,45,67,43,65,76,8};
		List<Integer> numbers = Arrays.asList(70,80,90,100,12,34,45,67);
		
		Arrays.sort(intArry,Collections.reverseOrder());
		Collections.sort(numbers,Collections.reverseOrder());
		
		Arrays.stream(intArry).forEach(value->{
			System.out.print(value+" ");
		});
		System.out.println("\n"+numbers);
		
		//Integer[] array2 = Arrays.stream(intArry).sorted(Comparator.reverseOrder()).collect(Collectors.toList()).toArray(Integer[]::new);
		Integer[] array2 = Arrays.stream(intArry).sorted(Collections.reverseOrder()).collect(Collectors.toList()).toArray(Integer[]::new);
		Arrays.stream(array2).forEach(value->{
			System.out.print(value+" ");
		});
	}

}
