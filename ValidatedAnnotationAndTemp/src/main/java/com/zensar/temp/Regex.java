package com.zensar.temp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Regex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String array = " java    is best. java    is too12     good45 java1 is object-oriented. good good good! ";
		array = array.replaceAll("-", " ") // replace all hyphen with space
				.replaceAll("[^a-zA-Z ]", "") // remove all special symbols
				.replaceAll("\\s+", " ") // remove multiple space and replace with single space
				//.replaceAll("[^a-zA-Z]+", " ") // This is the combination or equals of above 3 expression 
				.trim();
		System.out.println(array);
		Integer []intArray = {10,18,20,13,21,21,21,12,17,21,21,18};
		Optional<Integer> reduce = Arrays.stream(intArray).filter(val->val%2==0).reduce((a,b)->a+b);
		System.out.println(!reduce.isEmpty()?reduce.get():"reduce is empy");
		 Map<Integer, List<String>> collect5 = Arrays.stream(array.split(" ")).collect(Collectors.groupingBy(str->str.length(), Collectors.toList()));
		System.out.println(collect5);
		
		String str = "Hello!!! 123    abc-Java_is***   great.";
		String clean = str.replaceAll("[^a-zA-Z]+", " ").trim();
		System.out.println(clean);
		
		String str2 = "Hi!! I'm Shahab_123. Email:    shahab@gmail.com ### OK?";
		String cleaned = str2.replaceAll("[^a-zA-Z0-9@._]+", " ").trim();
		System.out.println(cleaned);
		
		String email = "shahab@gmail.com";
		boolean isValid = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
		System.out.println(isValid);
		
		String paragraph = "My email is shahab@gmail.com, please contact me!";
		Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		Matcher matcher = pattern.matcher(paragraph);
		if (matcher.find()) {
		    System.out.println("Found email: " + matcher.group());
		}
		System.out.println("========================================");
		String str1 = "shahab@site.com.info";
		Pattern p = Pattern.compile("\\.[a-zA-Z]{2,}");
		Matcher m = p.matcher(str1);
		if (m.find())
		    System.out.println("Matched: " + m.group());
		
		System.out.println("========================================");
		
		Pattern p1 = Pattern.compile("\\.[a-zA-Z]{2,}");
		Matcher m1 = p1.matcher("shahab@site.com.info");

		while (m1.find()) 
		    System.out.println("Matched: " + m1.group());
		



	}

}
