package com.zensar.temp;

import java.util.Arrays;

public class Testing {

	int m;

	public void setMarks(int marks) {
		if (marks < 0 || marks > 100)
			throw new IllegalArgumentException("You have entered negative value or greater than 100:"+marks);
		else
			m = marks;
	}

	public static void main(String[] args) {
		//Thread.sleep(-1); //java.lang.IllegalArgumentException: timeout value is negative
		Testing s1 = new Testing();
		s1.setMarks(45);
		System.out.println(s1.m);
		Testing s2 = new Testing();
		s2.setMarks(10);
		System.out.println(s2.m);
		String u="j";
		int a=10,b=20;
		a=a+b; b=a-b; a=a-b;
		System.out.println(a+" : "+b);
		String []str = "shashabuddin.pdf".split("[.]");
		System.out.println(str.length);
		System.out.println(Arrays.asList(str));
	}
}