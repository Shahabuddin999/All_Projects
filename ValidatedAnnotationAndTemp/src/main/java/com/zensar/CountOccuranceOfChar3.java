package com.zensar;

import java.util.stream.Collectors;
import java.util.*;
import java.util.function.Function;

public class CountOccuranceOfChar3 {
	public static void main(String args[]) {
		String str = "Communicatioon".toLowerCase();
		List<String> list = Arrays.asList(str.split(""));
		Map<String,Integer> map = list.stream().collect(Collectors.toMap(Function.identity(),initialValue->1, Math::addExact));
		System.out.println(map);
		
		 List<String> dup = list.stream().filter(val->Collections.frequency(list, val)>1).collect(Collectors.toList());
		 Collections.sort(list, Collections.reverseOrder());
		 Collections.sort(dup, Collections.reverseOrder());
	     System.out.println(list+":   dup: "+dup);
		
	}
}