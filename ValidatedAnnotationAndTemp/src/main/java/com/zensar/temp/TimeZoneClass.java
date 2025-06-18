package com.zensar.temp;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneClass {

	public static void main(String[] args) {
		// 1. Get current time in UTC (store this in DB)
		ZonedDateTime utcNow = ZonedDateTime.now(ZoneOffset.UTC);

		// 2. Later, convert to user's zone (for response/UI)
		// ZoneId userZone = ZoneId.of("America/New_York");
		ZoneId userZone = ZoneId.of("Asia/Kolkata");
		ZonedDateTime userTime = utcNow.withZoneSameInstant(userZone);

		// 3. Format for display
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
		String displayTime = userTime.format(formatter);
		System.out.println(displayTime);

	}

}
