package com.zensar.interview;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test1 {

	public static void main(String[] args) {
		HashMap<String, Integer> map = new LinkedHashMap<>();
		map.put("shahabuddin", 2);
		map.put("parvej", 2);
		map.put("nizam", 6);
		map.put("kalam", 4);
		map.put("kalam", 4);
		
		LinkedHashMap<String, Integer> collect = map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
		.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));

		System.out.println(collect);
		
		Map<String, Long> collect2 = List.of("shahab","shahab","kalam","kalam","parvej")
		.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		
		System.out.println(collect2);
		
	}

}
