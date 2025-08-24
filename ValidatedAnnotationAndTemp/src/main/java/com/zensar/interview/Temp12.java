package com.zensar.interview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Temp12 {

	public static void main(String[] args) {
		List<List<String>> asList = Arrays.asList(List.of("apple,banana".split(",")),List.of("orange,grape".split(",")));
		
		List<String> list = asList.stream().flatMap(lst->lst.stream()).collect(Collectors.toList());
		System.out.println(list);
		
		list.stream().forEach(str->{
			System.out.println(str);
		});
	}
}
