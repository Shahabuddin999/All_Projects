package com.zensar.java11;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Temp2 {

	public static void main(String[] args) {

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
		//.map(date->LocalDateTime.parse(date.substring(0,19),fmt))
		.filter(date->!(LocalDateTime.parse(date.substring(0,19),fmt)).isAfter(end) &&  !(LocalDateTime.parse(date.substring(0,19),fmt)).isBefore(start))
		.forEach(System.out::println);
		
		String formated = end.format(fmt);
		System.out.println(formated);
	}

}
