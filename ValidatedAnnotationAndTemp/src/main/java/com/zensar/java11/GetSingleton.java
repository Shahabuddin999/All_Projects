package com.zensar.java11;

public class GetSingleton {

	public static void main(String[] args) {
		Person p = new Person(10, "Shahab");
		System.out.println(p.getName()+" : "+p.getId());
	}
}
