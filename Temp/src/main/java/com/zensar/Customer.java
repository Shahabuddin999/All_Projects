package com.zensar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Customer {

	private int age;
	private String name;
	private List<String> phoneNumbers;

	public Customer(int age, String name, List<String> phoneNumbers) {
		this.age = age;
		this.name = name;
		this.phoneNumbers = phoneNumbers;
	}

	public Customer() {
	}

	public static List<Customer> getCustomers() {
		return Stream.of(new Customer(10, "Shahab", Arrays.asList("23312310", "231313")),
				new Customer(20, "Kalam", Arrays.asList("23324340", "45454")),
				new Customer(30, "Nizam", Arrays.asList("454565", "87687")),
				new Customer(40, "Parvej", Arrays.asList("2324", "6885"))).collect(Collectors.toList());
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Override
	public String toString() {
		return "Customer [age=" + age + ", name=" + name + ", phoneNumbers=" + phoneNumbers + "]";
	}

}
