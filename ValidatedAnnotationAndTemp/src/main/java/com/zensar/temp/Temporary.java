package com.zensar.temp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Temporary {
    public static void main(String[] args) {
    	List<String> list = List.of("mumbai","agra","delhi");
    	Map<String, Long> collect = Arrays.stream(String.join("", list).split(""))
    			.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    	System.out.println(collect);
    	
    	Map<String, Map<String, Long>> collect2 = list.stream().collect(Collectors.toMap(Function.identity(), str-> {
    		 return Arrays.stream(str.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    	}));
    	System.out.println(collect2);
    }
}
