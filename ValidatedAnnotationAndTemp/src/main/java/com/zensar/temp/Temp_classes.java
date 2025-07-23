package com.zensar.temp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
public class Temp_classes {

	public static void main(String[] args) {
		        String paragraph = "Java is great Java is object-oriented Java is fast and Java is powerful powerful powerful";
		        List<String> list = List.of(paragraph.split(" "));//Arrays.asList(paragraph.split(" "));
		        Map<String, Long> count = list.stream().filter(str->Collections.frequency(list, str)>3)
		                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		        System.out.println(count);
	}
}
