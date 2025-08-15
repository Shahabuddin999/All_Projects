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
		
		String paragraph = "Java is great. Java is object-oriented. Java is fast and Java is powerful!";
        int topN = 3;
        //System.out.println(paragraph);
        // 1. Clean and normalize the paragraph
        String cleaned = paragraph.replaceAll("[^a-zA-Z0-9\\s]", "")  // remove punctuation
                                  //.replaceAll("-", "")             // remove hyphens
                                  .toLowerCase();                   // convert to lowercase

        System.out.println(cleaned);
        paragraph="shahbuddin ansari@gmail.com ye mera mail hai";
        String mail = paragraph.replaceAll("[^\\w+@\\.\\w ]", "");
        System.out.println(mail);
        

	}
}