package com.zensar.interview;

import java.util.List;
import java.util.stream.Collectors;

public class Temp20 {

	//  sort by ascen by len
	public static void main(String[] args) {
		List<String> list = List.of("Abhay Raj","Chandrashekhar","Suresh","Raj kumar","Shahabuddin");
		List<String> collect = list.stream().sorted((n1,n2)->n1.length()-n2.length()).collect(Collectors.toList());
		System.out.println(collect);
	}

}
