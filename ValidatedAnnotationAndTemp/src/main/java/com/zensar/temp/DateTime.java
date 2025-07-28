package com.zensar.temp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DateTime {

	public static void main(String[] args) {
		
		DateTimeFormatter formatterDT = DateTimeFormatter.ofPattern("dd-MM-yyyy : HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.now(); 
		String formattedDate = dateTime.format(formatterDT);
		System.out.println(formattedDate); 
		
		String dateString = "20-10-2025 : 23:20:30"; 
		dateTime = LocalDateTime.parse(dateString,formatterDT);
		System.out.println(dateTime);
		dateString = dateTime.format(formatterDT);
		System.out.println(dateString);
		
		System.out.println("=========================================================");
		
		
		List<String> logRecords = Arrays.asList(
	            "2023-06-21 14:35:00 INFO Application started successfully",
	            "2023-06-21 14:40:00 DEBUG Loading configuration",
	            "2023-06-21 14:45:00 INFO Application shutdown",
	            "2023-06-21 14:50:00 ERROR Failed to load resource"
	        );
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime start = LocalDateTime.parse("2023-06-21 14:35:00",formatter);
		LocalDateTime end = LocalDateTime.parse("2023-06-21 14:45:00",formatter);
		List<LocalDateTime> collect = logRecords.stream()
				.map(val->val.replaceAll("[^0-9: -]", "").trim())
				.map(val->LocalDateTime.parse(val,formatter))
				.filter(date-> (date.isAfter(start) || date.equals(start)) && (date.isBefore(end) || date.equals(end)))
				.collect(Collectors.toList());
		collect.stream().forEach(date->System.out.println(date.format(formatter)));
		
		System.out.println("=========================================================");
		
		
//		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a z");
//		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
//		System.out.println(zdt);
//		String format = zdt.format(fmt);
//		System.out.println(format);
//		
//		zdt = ZonedDateTime.parse("12-09-2025 12:00:45 AM IST",fmt);
//		System.out.println(zdt);
//		format = zdt.format(formatter);
//		System.out.println(format);
		
		
		///////////////////////////////
		//DateTimeFormatter.ofPattern("hh:mm:ss VV"); // ❌ no AM/PM marker
		//hh is 12-hour format, but there’s no a → ambiguous error
		//✅ Should use HH (24-hour) if no AM/PM
		
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a VV", Locale.ENGLISH);
        // Locale.ENGLISH is specified for AM/PM in english otherwise it would take system's default Local that might be different, so now it is fixed
        // Locale.HINDI is AM → पूर्वाह्न, PM → अपराह्न and for Locale.JAPANESE it is 午前 / 午後 (Gozen/Gogo)
        // VV will take dynamic Zone like "Asia/Kolkata" or "America/New_york" 
        
        //ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        //ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/New_York")); // America/New_York OR Asia/Kolkata is pointing to VV in DateTimeFormatter
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.systemDefault()); // This will pick system's default Zone like Asia/Calcutta
        //System.out.println("Original ZDT: " + zdt);
        String formatted = zdt.format(fmt);
        System.out.println("Original Formatted: " + formatted); 

        String input = "10-07-2025 01:11:07 AM Asia/Kolkata";
        //String input = "10-07-2025 11:45:00 午前 Asia/Tokyo";  // Japanese for AM. Fails because:You used 午前 instead of AM. Your Locale is ENGLISH, so it expects AM/PM

        ZonedDateTime parsed = ZonedDateTime.parse(input, fmt);
        //System.out.println("Parsed ZDT: " + parsed);

        String reformatted = parsed.format(fmt);
        System.out.println("Assigned Formatted: " + reformatted);
        
        System.out.println("====================Practice=======================");
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a VV", Locale.ENGLISH);
        ZonedDateTime dt = ZonedDateTime.now(ZoneId.systemDefault());
        String fmtd = dt.format(fm);
        System.out.println(fmtd);
        
        String newdt = "10-10-2025 10:50:20 AM Asia/Kolkata";
        ZonedDateTime newdtime = ZonedDateTime.parse(newdt,fm);
        String fmtddt = newdtime.format(fm);
        System.out.println(fmtddt);
        
//        Set<String> zones = ZoneId.getAvailableZoneIds();
//        zones.stream().sorted().forEach(System.out::println);
        
        DateTimeFormatter frmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse("2023-06-21 14:35:00",frmt);
        LocalDateTime endDate = LocalDateTime.parse("2023-06-21 14:45:00",frmt);
        
        logRecords.stream()
        		  //.map(date->LocalDateTime.parse(date.substring(0,19),frmt))
        		  .map(date->LocalDateTime.parse(date.replaceAll("[^0-9: -]", "").trim(),frmt))
        		  .filter(date-> !date.isAfter(endDate) && !date.isBefore(startDate))
        		  .forEach(System.out::println);
	}

}
