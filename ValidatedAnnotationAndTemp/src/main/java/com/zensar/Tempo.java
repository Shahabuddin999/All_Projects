package com.zensar;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tempo {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Shahab","Shera","Nizam","Alam","Afsar","Intiyaj");
		Map<Character, List<String>> map = list.stream().collect(Collectors.groupingBy(val->val.charAt(0)));
		System.out.println(map);
	}
}
