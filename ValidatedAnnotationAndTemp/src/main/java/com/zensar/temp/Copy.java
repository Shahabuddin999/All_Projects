package com.zensar.temp;

import java.util.Date;

public class Copy implements Cloneable{
	
	private int age;
	private String name;
	private Date date;
	@Override
	public Object clone() throws CloneNotSupportedException {
		Copy copy = (Copy)super.clone();
		return copy;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Copy [age=" + age + ", name=" + name + ", date=" + date + "]";
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Copy copy = new Copy();
		copy.setAge(20);
		copy.setName("Shahab");
		copy.setDate(new Date("05/20/2023"));
		Copy copy1=(Copy) copy.clone();
		copy1.name="Ansari";
		System.out.println(copy1);
		System.out.println(copy);
	}

}
