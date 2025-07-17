package com.zensar.java11;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Temp {

	public static void main(String[] args) {
		Map<String, Long> collect = List.of("AANAYADD","ATHAH","AAVANA","ATACK","AJAAYA","AKIRA","AARAV","AJAYYY")
		.stream()
		.filter(val->val.toLowerCase().startsWith("aa")).collect(Collectors.toMap(Function.identity(), value->{
			return value.chars().filter(v->!"AEIOU".contains(String.valueOf((char)v))).count();
		}));
		System.out.println(collect);	
		
		System.out.println("=======Prime===========");
		IntStream.rangeClosed(2, 10).filter(number->{
			return IntStream.rangeClosed(2, (int)Math.sqrt(number)).noneMatch(devide->number%devide==0);
		}).forEach(prime->{
			System.out.println("Prime: "+prime);
		});
		
		System.out.println("=======Armstrong===========");
		IntStream.of(1, 40,153,370,371,407,50).filter(number->{
			return number == String.valueOf(number).chars().map(val->(int)Math.pow(Character.getNumericValue(val), 3)).sum();
		})
		.forEach(System.out::println);
		
		System.out.println("==================");
		System.out.println(Character.getNumericValue(49));
		
		System.out.println("==================");
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
		//.map(date->date.substring(0,19))
		.map(date->date.replaceAll("[^0-9: -]", "").trim())
		.map(date->LocalDateTime.parse(date,fmt))
		.filter(date->!date.isAfter(end) && !date.isBefore(start))
		.map(date->date.format(fmt))
		.forEach(System.out::println);
		
		String name = "Shahabuddin Ansari koraon Allahabad";
		Map<String, Integer> collect2 = Arrays.stream(name.split(" ")).collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(collect2);
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("shahab", 5);
		map.put("kalam", 4);
		map.put("baba", 7);
		
		map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue())
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new)).forEach((key,value)->{
			System.out.println(key+":"+value);
		});
		
		String palindrom ="madam sis rahmrahm sfd";
				Arrays.stream(palindrom.split(" ")).filter(str->{
					return IntStream.range(0, str.length()/2).allMatch(i->str.charAt(i) == str.charAt(str.length()-i-1));
				}).forEach(str->System.out.println("Palindrom: "+str));
		System.out.println("======================");	
		
		Arrays.stream(palindrom.split(" ")).filter(str->str.equals(new StringBuilder(str).reverse().toString())).forEach(System.out::println);
	
		List<String> collect3 = IntStream.rangeClosed(1, 10).filter(v->v%2==0).mapToObj(val->"Even:"+val).collect(Collectors.toList());
		System.out.println(collect3);
	}
}
