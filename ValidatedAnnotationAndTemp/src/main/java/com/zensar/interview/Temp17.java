package com.zensar.interview;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Temp17 {

	public static void main(String[] args) {

		String str = "heeeeeliilo";
		//Output ="eeellho";  
		
		Map<String, Long> collect = Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		LinkedHashMap<String, Long> collect2 = collect.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed())
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(a1,a2)->a1,LinkedHashMap::new));
		collect2.entrySet().stream().forEach(string->{
			System.out.print(string.getKey().repeat(Integer.parseInt(string.getValue()+"")));
		});
		// single list, acending without duplicate
		List<Integer> list1 = Arrays.asList(3,5,8,3,2);
		List<Integer> list2 = Arrays.asList(11,12,9,3,2);
		List<List<Integer>> list3 = List.of(list1,list2);
		List<Integer> collect3 = list3.stream().flatMap(lst->lst.stream()).distinct().sorted().collect(Collectors.toList());
		System.out.println(collect3);
		
		System.out.println("---------------------");
		String string = "\u2005Hello World\u2005"; // \u2005 = Unicode whitespace
        System.out.println("Before strip: [" + string + "]");
        System.out.println("After strip:  [" + string.strip() + "]");
        System.out.println("After trim:   [" + string.trim() + "]");
        
        
        System.out.println(Optional.ofNullable(null));
        Optional<String> op = Optional.of("Shahab");
        // System.out.println(Optional.empty(null)); // Compile time error
        System.out.println(Optional.of(null));
       
	}
}




