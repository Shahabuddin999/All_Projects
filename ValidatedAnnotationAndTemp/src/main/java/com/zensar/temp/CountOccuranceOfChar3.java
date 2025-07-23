package com.zensar.temp;

import java.util.stream.Collectors;
import java.util.*;
import java.util.function.Function;

public class CountOccuranceOfChar3 {
	public static void main(String args[]) {
		String str = "Communicatioon".toLowerCase();
		
		 
	     str = "Communicatioon".toLowerCase();
	     Map<String, Long> collect = Arrays.stream(str.split(""))
	    		 						   .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(collect);
		
		Map<String, Long> collect2 = collect.entrySet().stream().filter(entry->entry.getValue()>1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println(collect2);
	}
}