package com.zensar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TempClass {

	public static void main(String[] args) {
		
		final List<String> list = new ArrayList<>();
		list.add("aa");list.add("aab");list.add("aad");list.remove(0);
		List<String> list1 = list;
		list1.add("aaba");
		System.out.println(list1);
		Map<Integer, String> map = new LinkedHashMap<>();
		map.put(3, "aa");
		map.put(2, "bb");
		map.put(1, "cc");
		Map<Integer, String> map1=map.entrySet().stream().sorted(Map.Entry.<Integer, String>comparingByValue().reversed()).
				collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1, LinkedHashMap::new));
		System.out.println(map1);
	}
}
