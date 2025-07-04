package com.zensar.java11;

import java.util.List;

public class Temp {

	public static void main(String[] args) {
//		List<String> list = List.of("AANAYA","ATHAH","AAVANA","ATACK","AJAAYA","AKIRA","AARAV","AJAYYY");
//		List<String> collect = list.stream().filter(val-> val.toLowerCase().startsWith("aa")).collect(Collectors.toList());
//		System.out.println(collect);
//		
		//List<String> list = List.of("AANAYA","ATHAH","AAVANA","ATACK","AJAAYA","AKIRA","AARAV","AJAYYY");
		List.of("AANAYA","ATHAH","AAVANA","ATACK","AJAAYA","AKIRA","AARAV","AJAYYY")
		.stream()
		.filter(val->val.toLowerCase().startsWith("aa"))
		.forEach(System.out::println);
		
		
		String str1="Hello"; 
		String str2="Hello"; 
		String str3=new String("Hello"); 
		System.out.println(str1==str2);
		System.out.println(str1==str3);
	}
}
