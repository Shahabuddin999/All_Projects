package com.zensar.temp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tempo {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Shahab","Shahab","Shera","Nizam","Alam","Afsar","Intiyaj");
		
		Map<Character, List<String>> map = list.stream().collect(Collectors.groupingBy(val->val.charAt(0)));
		System.out.println(map);
		
		Map<String, Integer> collect = list.stream().collect(Collectors.groupingBy(v->v,Collectors.summingInt(v->1)));
		System.out.println(collect);
	}
}
