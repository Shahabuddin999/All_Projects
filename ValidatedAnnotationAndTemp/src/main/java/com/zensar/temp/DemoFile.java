package com.zensar.temp;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DemoFile {
	public static void main(String[] args) {
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("Shahabuddin", 1);
		map.put("Ansari", 3);
		map.put("Kalam", 2);
		map.put("Nizam", 8);
		map.put("Parwej", 9);
		
		Map<String, Integer> sorted = map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1, LinkedHashMap::new));
		System.out.println(sorted);
		
		List<String> list = Arrays.asList("Shahabuddin","Ansari","Ansari","Kalam","Nizam");
		List<String> sortedList = list.stream().sorted((key, value)->key.compareTo(value)).collect(Collectors.toList());
		System.out.println(sortedList);
		sortedList = list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		System.out.println(sortedList);		
		
		Map<String, Integer> count = list.stream().collect(Collectors.toMap(Function.identity(),initialValue->1, Math::addExact));
		System.out.println(count);
		
		List<String> duplicate= list.stream().filter(value->Collections.frequency(list, value)>1).collect(Collectors.toList());
		System.out.println(duplicate);
		
		Predicate<Integer> predicate = (value)->{
			return value>100?true:false;
		};
		System.out.println("Predicate : "+ predicate.test(10));
		
		Function<Double, Integer> fun = (value)->{
			return (int) (value*value);
		};
		System.out.println("Function : "+ fun.apply((double) 10));
		
		Supplier<Integer> supplier = ()->{
			return 10*10;
		};
		System.out.println("Supplier : "+supplier.get());
		
		Consumer<Integer> consumer = (value)->{
			if(value>100)
				System.out.println("Consumer : Passed");
			else 
				System.out.println("Consumer : Failled");
		};
		consumer.accept(110);
		
		int max = Arrays.asList(10,30,50,20,40).stream().max((e1,e2)->e1-e2).get();
		System.out.println("Max : "+max);
		Integer[]arr = (Integer[]) Arrays.asList(10,30,50,20,40).toArray();
		Integer[]arr1 = Arrays.asList(10,30,50,20,40).toArray(Integer[]::new);
		System.out.println(arr[0]);
	}	
}
