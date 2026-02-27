package com.zensar.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;
public class Temp25 {

	public static void main(String[] args) {
		System.out.println("OK");
		
		int  []arr={-1, 4, 6, -5, -7, 8, -9, 0};
	      List<Integer> list = Arrays.stream(arr).filter(num->num<0).boxed().collect(Collectors.toList());
	      
	      List<Integer> list2 = Arrays.stream(arr).filter(num->num>=0).boxed().collect(Collectors.toList());
	      
	      List<Integer> list3 = List.of(list,list2).stream()
	      .flatMap(lst->lst.stream()).collect(Collectors.toList());
	      
	      list3.addAll(list2);
	      List<Integer> list4 = Arrays.stream(list3.toArray(Integer[]::new))
	    	      .map(lst->lst).collect(Collectors.toList());
	      
	      System.out.println(list4);
	      
	      
	      String name ="shahabuddin";
			List<String> list5 = Arrays.asList(name.split(""));
			// non-repeated with position
			String collect = Arrays.stream(name.split("")).filter(ch->Collections.frequency(list5, ch)==1).collect(Collectors.joining());
			System.out.println(collect);
			Arrays.stream(collect.split("")).map(ch->{
				int index = name.indexOf(ch);
				return ch +" : "+index;
			}).forEach(System.out::println);
			
			
			Map<String, Integer> collect2 = Arrays.stream(name.split(""))
			.filter(ch->Collections.frequency(Arrays.asList(name.split("")), ch)==1).sorted((s1,s2)->s2.compareTo(s1))
			.collect(Collectors.toMap(Function.identity(), ch->name.indexOf(ch),(e1,e2)->e1,LinkedHashMap::new));
			
			System.out.println(collect2);
			
	}
}
