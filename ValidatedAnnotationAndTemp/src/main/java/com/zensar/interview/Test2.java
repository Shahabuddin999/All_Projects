package com.zensar.interview;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test2 {

	public static void main(String[] args) {
		List<Integer> list = List.of(10,30,20,60);
		list.stream().sorted(Collections.reverseOrder()).skip(2).limit(1).forEach(System.out::println);
		
		IntStream.rangeClosed(2, 10).forEach(num->{
			boolean noneMatch = IntStream.rangeClosed(2, (int)Math.sqrt(num)).noneMatch(devide->num%devide==0);
			if(noneMatch)
				System.out.println("Prime:"+num);
		});
		
		IntStream.rangeClosed(2, 500).forEach(num->{
			int reduce = String.valueOf(num).chars()
					.map(ch->(int)Math.pow(Character.getNumericValue(ch), 3))
					.reduce(0, (a,b)->a+b);
			if(reduce == num)
				System.out.println("Arm:"+num);
		});
		
		
		List.of("AASFS","EFSF","FSSS").stream().forEach(str->{
			long count = Arrays.stream(str.split("")).filter(st->!"AEIOU".contains(st)).count();
			System.out.println(str+":"+count);
		});
		
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("Shahab",10);map.put("Nizam",5);map.put("Kalam",4);map.put("Parvej",30);
		LinkedHashMap<String, Integer> collect = map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
		.collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a1,a2)->a1,LinkedHashMap::new));
		
		collect.entrySet().stream().skip(2).limit(1).forEach(entry->System.out.println(entry.getKey()+":"+entry.getValue()));
		System.out.println(collect);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
		LocalDateTime local = LocalDateTime.now();
		String format = local.format(formatter);
		System.out.println(format);
		
		List<String> logRecords = Arrays.asList(
	            "2023-06-21 14:35:00 INFO Application started successfully",
	            "2023-06-21 14:40:00 DEBUG Loading configuration",
	            "2023-06-21 14:45:00 INFO Application shutdown",
	            "2023-06-21 14:50:00 ERROR Failed to load resource"
	        );
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime start = LocalDateTime.parse("2023-06-21 14:35:00",fmt);
		LocalDateTime end = LocalDateTime.parse("2023-06-21 14:45:00",fmt);
		
		logRecords.stream()
		.filter(dt->!LocalDateTime.parse(dt.substring(0, 19),fmt).isAfter(end) && !LocalDateTime.parse(dt.substring(0, 19),fmt).isBefore(start))
		.forEach(System.out::println);
		
		Map<Entry<String, Integer>, Long> collect2 = map.entrySet().stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println(collect2);
		
		
		List<String> lst = List.of("Shahab","Ansari","Koraon");
		Map<String, Integer> collect3 = lst.stream().collect(Collectors.toMap(Function.identity(),String::length));
		System.out.println(collect3);
		
//		List<String> listCon = new ArrayList<>(List.of("A", "B", "C"));
//		for (String s : listCon) {
//		    listCon.remove("A"); // java.util.ConcurrentModificationException
//		}
		
	}

}
