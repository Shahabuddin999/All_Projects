package com.zensar.interview;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Temp33 {

	public static void main(String[] args) {
		Map<Integer,Long> map= List.of(0,0,0,0,0,1,2,2,3,3,3,4,4,4,4,5,5,5,5,5,6,6,6,6).stream()
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
				
				
				map.entrySet().stream().sorted(Map.Entry.<Integer,Long>comparingByValue().reversed()).limit(3)
				.forEach(entry->{
					System.out.println(entry.getKey() +":"+entry.getValue());
				});
	}
}
