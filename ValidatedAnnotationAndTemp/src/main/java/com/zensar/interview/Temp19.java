package com.zensar.interview;

public class Temp19 {
	public static void main(String[] args) {
		 String str = "hello world";

	        if (str != null && str.length() > 1) {
	            String result = str.substring(0, 1).toUpperCase()          // first char
	                          + str.substring(1, str.length() - 1)        // middle
	                          + str.substring(str.length()-1,str.length()).toUpperCase(); // last char
	            System.out.println(result);
	        } else if (str.length() == 1) {
	            System.out.println(str.toUpperCase()); // single char string case
	        }
	        
	        System.out.println(str.substring(str.length()-1));
	}
}
