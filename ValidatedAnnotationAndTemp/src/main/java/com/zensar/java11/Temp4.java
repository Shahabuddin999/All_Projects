package com.zensar.java11;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Temp4 {
	public static void main(String[] args) {
		String name = "My name is Shahabuddin";
		Map<String, Integer> collect = Arrays.stream(name.split(" ")).collect(Collectors.toMap(Function.identity(), String::length));
		System.out.println(collect);
	}
}
