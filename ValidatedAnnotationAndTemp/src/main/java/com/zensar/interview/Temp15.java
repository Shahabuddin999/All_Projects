package com.zensar.interview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Temp15 {

	int show(String num) {
		String collect = Arrays.asList(num.split("")).stream().sorted((a1,a2)->a2.compareTo(a1)).collect(Collectors.joining());
		return Integer.parseInt(collect);
	}
	public static void main(String[] args) {

		int num = new Temp15().show("0324");
		System.out.println(num);
	}

}
