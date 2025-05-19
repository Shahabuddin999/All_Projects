package com.zensar;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Demo_class {
	public static void main(String[] args) {
		String arr[] = { "practice.geeksforgeeks.org", "www.geeksforgeeks.org", "code.geeksforgeeks.org" };
		// Sorts arr[] in ascending order
		Arrays.sort(arr);
		System.out.printf("Modified arr[] : \n%s\n\n", Arrays.toString(arr));
		// Sorts arr[] in descending order
		Arrays.sort(arr, Collections.reverseOrder());
		System.out.printf("Modified arr[] : \n%s\n\n", Arrays.toString(arr));
		
		String[] str = {"shahab","ansari","koraon","shahab","alld","alld"};
		List<String> nameList = Arrays.asList(str);
		// Get unique
		List<String> unique = nameList.stream().distinct().collect(Collectors.toList());
		System.out.println(unique);
		List<String> duplicate = nameList.stream().filter(name->Collections.frequency(nameList, name)>1).collect(Collectors.toList());
		System.out.println(duplicate);
		Map<String, Integer> map = nameList.stream().collect(Collectors.toMap(Function.identity(), intial->1,Math::addExact));
		// getting duplicate
		List<Entry<String, Integer>> duplicateNames = map.entrySet().stream().filter(name->name.getValue()>1).collect(Collectors.toList());
		System.out.println(duplicateNames +"////");
		
		System.out.println(map);
		Optional<Entry<String, Integer>> entry = map.entrySet().stream().max((a,b)->a.getValue()-b.getValue());
		System.out.println("max : "+entry.get().getKey()+" : "+ entry.get().getValue());
		Map<String, Integer> sorting = map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a,b)->a, LinkedHashMap::new));
		System.out.println(sorting);
		
		String s1="hello word";
		String s2="hello ".concat("word");
		System.out.println(s1==s2);
		
		System.out.println(nameList);
		Comparator<String> cmp = (a,b)->a.compareTo(b);
		Collections.sort(nameList,cmp);
		System.out.println(nameList);
		String aa="aa";String bb = new String("aa");
		System.out.println(aa.equals(bb));
		
	}

	@Override
	public boolean equals(Object o) {
		return true;
	}
	@Override
	public int hashCode() {
		return 1;
	}
}