package com.zensar;
import java.util.*;
import java.util.stream.Collectors.*;
import java.util.stream.*;
public class Temp {
	static int i = 2;
    public static void printNumber(int x)
    {
        printNumber(i);
    }
	public static void main(String[] args) {
		Map<String, Integer> wordCounts = new HashMap<>();
		wordCounts.put("A", 100);
		wordCounts.put("B", 200);
		wordCounts.put("M", 50);
		wordCounts.put("D", 70);
		wordCounts.put("F", 200);
		Map<String, Integer> sortedByCount = wordCounts.entrySet()
		         .stream()
		         .sorted((Map.Entry.<String, Integer>comparingByKey().reversed()))
		         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println(sortedByCount);
		// printNumber(i); // java.lang.StackOverflowError
		List<String> list = Arrays.asList("AAA","ssss","wwwnnnnnnw","wwwwaaaaa");
		Optional<String> str = list.stream().max((a,b)->a.length()-b.length());
		System.out.println(str.get());
		System.out.println(Arrays.asList("AAA","ssss","wwwnnnnnnw","wwwwaaaaa").stream().min((a,b)->a.length()-b.length()).get());
		List<String> decending = list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		List<String> asending = list.stream().sorted().collect(Collectors.toList());
		System.out.println(decending);
		System.out.println(asending);
	}
}
