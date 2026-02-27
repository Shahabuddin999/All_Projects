package com.zensar.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Temp10 {

	public static void main(String[] args) {

		String string = "1 10 100 22 33 101 506 44 2222 1111 444444 666666 7777777 55543 55555";
		
		Map<Integer, String> map3 =
		        Arrays.stream(string.split(" "))
		                .sorted((s1,s2)->s1.length()-s2.length())
		                .collect(Collectors.groupingBy(
		                        data->data.length(),                       
		                        Collectors.joining(" ")
		                ));

		map3.entrySet().stream().forEach(entry->System.out.println(entry.getValue()));
		System.out.println("-----------------");
		
		 Map<Integer, Long> collect = Arrays.stream(string.split(" "))
		                .sorted((s1,s2)->s1.length()-s2.length())
		                .collect(Collectors.groupingBy(data->data.length(),Collectors.counting()));

		 collect.entrySet().stream().forEach(entry->System.out.println(entry));
		System.out.println("-----------------");
		
		Map<Integer,String> map = new LinkedHashMap<>();
		
		Arrays.stream(string.split(" ")).sorted((st1,st2)->st1.length()-st2.length()).forEach(str->{
			
			Integer index = str.length();
			if(map.containsKey(index)) 
				map.replace(index, map.get(index)+" "+str);
			 else 
				map.put(index, str);
			
		});
		map.entrySet().stream().forEach(mp->{
			System.out.println(mp.getValue().replace("null ", ""));
		});
		Map<Integer, Integer> mp = new HashMap<>(); 
		mp.put(8, 4);
		mp.put(5, 5);
		mp.put(2, 9);
		mp.put(1, 0);
		
		Map<Integer, Integer> collect2 = mp.entrySet().stream()
				.sorted((e1,e2)->e2.getKey()-e1.getKey())
				.collect(Collectors.toMap(e->e.getKey(),e->e.getValue(),(e1,e2)->e1,LinkedHashMap::new));
		System.out.println(collect2);
	}

}
