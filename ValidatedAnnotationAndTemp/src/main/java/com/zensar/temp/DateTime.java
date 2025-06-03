package com.zensar.temp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTime {

	public static void main(String[] args) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate date = LocalDate.now(); // current date
		String formatted = date.format(formatter); // format it

		System.out.println(formatted); // e.g., "02-06-2025"
		
		System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}

}
