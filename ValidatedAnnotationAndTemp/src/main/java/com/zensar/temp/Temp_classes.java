package com.zensar.temp;
import java.util.*;
import java.util.stream.Collectors;
public class Temp_classes {

	public static void main(String[] args) {
		        String paragraph = "Java is great Java is object-oriented Java is fast and Java is powerful powerful powerful";
		        List<String> list = Arrays.asList(paragraph.split(" "));
		        Map<String, Integer> count = list.stream().filter(str->Collections.frequency(list, str)>3)
		                .collect(Collectors.groupingBy(word -> word, Collectors.summingInt(s->1)));
		        System.out.println(count);
	}
}
