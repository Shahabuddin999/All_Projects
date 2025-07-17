package com.zensar.java11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPractice {

	public static void main(String[] args) {
		String text = "-Contact us at support@example.com or admin@my@domain.org.in bb";
		Pattern pattern1 = Pattern.compile("[a-zA-Z0-9._]+@[a-zA-Z.]+\\.[a-zA-Z]{2,}"); // All mail will be returned because it will check in whole string
		Matcher matcher1 = pattern1.matcher(text);
		while(matcher1.find()) {
			System.out.println(matcher1.group());
		}
		System.out.println("====================================");
		text = "aman@example.com Contact us at support@example.com or admin@my@domain.org.in bb";
		pattern1 = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z.]+\\.[a-zA-Z]{2,}"); // here ^ will check the mail only in starting of text if not found in starting will return fall
		matcher1 = pattern1.matcher(text);
		while(matcher1.find()) {
			System.out.println(matcher1.group());
		}
		System.out.println("====================================");
		String phone = "qw1234567890yy";
		Pattern pattern2 = Pattern.compile("\\d{10}"); // This expression is looking/checking for 10 digits contiguous/continuously in entire string
		Matcher matcher2 = pattern2.matcher(phone);
		if(matcher2.find())
			System.out.println("checking phone number in entire string: "+matcher2.group());
		
		System.out.println("====================================");
		phone = "1234567890";
		pattern2 = Pattern.compile("^\\d{10}$"); // This expression is looking/checking for 10 digits contiguous/continuously from starting to ending
		matcher2 = pattern2.matcher(phone);
		if(matcher2.find())
			System.out.println("phone number in from starting to ending only 10 digit: "+matcher2.group());
		
		System.out.println("====================================");
		text = "java 17 is a very good language!";
		Pattern pattern3 = Pattern.compile("[a-zA-z]+"); // [a-zA-z]+ will return word by word if you add + just after [] like []+, if you dont add + then you will get char by char
		Matcher matcher3 = pattern3.matcher(text);
		while(matcher3.find())
			System.out.println(matcher3.group());
		System.out.println("====================================");
		
		// ^[a-zA-Z_0-9]+ it checks only starting value should be allowed char then it can be anything you will receive via matcher.group()
		text = "user_123#";
		pattern3 = Pattern.compile("^[a-zA-Z_0-9]+"); 
		matcher3 = pattern3.matcher(text);
		System.out.println(matcher3.matches());
		if(matcher3.find())
			System.out.println(matcher3.group()); // Here you will receive value 'user_123'
		
		System.out.println("====================================");
		
		// ^[a-zA-Z_0-9]+$ check entire string should be allowed only with given char 
		text = "user_123#"; 
		pattern3 = Pattern.compile("^[a-zA-Z_0-9]+$"); 
		matcher3 = pattern3.matcher(text);
		System.out.println(matcher3.matches()); // false 
		if(matcher3.find())
			System.out.println(matcher3.group()); // you will not get anything because you have used #.
		// if you remove # from  "user_123#" like keep it like  "user_123" then you will get true and output as user_123
		
		System.out.println("====================================");
		
		pattern3 = Pattern.compile("[^a-zA-z0-9 ]+");
		matcher3 = pattern3.matcher("shahabudd!in-_+ 123 @#");
		while(matcher3.find())
			System.out.println(matcher3.group());
		
		String str = "absdm 12 cs1dc45 dsad432";
		Pattern ptr = Pattern.compile("[0-9]+");
		Matcher mtr = ptr.matcher(str);
		while(mtr.find())
			System.out.println(mtr.group());
	}
}
