package com.zensar.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;
public class Temp25 {

	public static void main(String[] args) {
		System.out.println("OK");
		
		int  []arr={-1, 4, 6, -5, -7, 8, -9, 0};
	      List<Integer> list = Arrays.stream(arr).filter(num->num<0). boxed().collect(Collectors.toList());
	      
	      List<Integer> list2 = Arrays.stream(arr).filter(num->num>=0). boxed().collect(Collectors.toList());
	      
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
			Arrays.stream(collect.split("")).map(ch->{
				int index = name.indexOf(ch);
				return ch +" : "+index;
			}).forEach(System.out::println);
	}
}
