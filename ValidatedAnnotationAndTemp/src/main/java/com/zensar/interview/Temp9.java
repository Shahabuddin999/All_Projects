package com.zensar.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
public class Temp9 {
	private int age;
	private String name;
	private String address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	Temp9(Builder build){
		this.name = build.getName();
		this.age=build.getAge();
		this.address =build.getAddress();
	}
	
	@Override
	public String toString() {
		return "Temp9 [age=" + age + ", name=" + name + ", address=" + address + "]";
	}

	public static class Builder{
		int age;
		String name;
		String address;
		
		public int getAge() {
			return age;
		}

		public Builder setAge(int age) {
			this.age = age;
			return this;
		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public String getAddress() {
			return address;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		Builder(){
			
		}
		Temp9 build() {
			return new Temp9(this);
		}
		
	}
	
	public static void main(String[] args) {
		Temp9 emp = new Builder().setAge(10).setName("Shahab").setAddress("Allahabad").build();
		System.out.println(emp);
	}
}
