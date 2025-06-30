package com.zensar.java11;

sealed class Vehicle permits Car,Bike {}

final class Car extends Vehicle {} // Subclasses must declare final, sealed, or non-sealed

final class Bike extends Vehicle {}

record User(String name, int age) {
	public User(String name) {
		this(name, 0); // must calls canonical constructor
	}

	public User(int age) {
		this("", 0); // must calls canonical constructor
	}

	public User() {
		this("", 0); // must calls canonical constructor
	}
}

public class Java17 {
	static void handle(Object obj) {
		  // int i = (int) obj; Here type casting is required
		  if (obj instanceof String s) // Pattern Matching Instead 
		        System.out.println("It is a string: " + s);
		  else if(obj instanceof Integer i) // But type casting is not required internally its being done
			  System.out.println("This is Integer: "+ i);
		
	}
	public static void main(String[] args) {
		// Text Blocks
		String json = """
				{
				  "name": "Shahab",
				  "age": 25
				}
				""";
		System.out.println(json);
		new User("", 12);
		new User("");
		new User(10);
		handle(10);
		
	}
}
// in java17 : sealed class, record type, pattern matching instanceof, text block