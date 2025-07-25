package com.zensar.java11;

import java.util.List;
import java.util.stream.IntStream;

class Employee{
	//@Value
	String name;
	String department;
	Integer salary;
	Employee(String name, String department, Integer salary){
		this.name=name;
		this.department=department;
		this.salary=salary;
	}
	
}
public class TempClass {

	public static void main(String[] args) {
		List<Employee> list = List.of(new Employee("Ramesh","IT",40000),new Employee("Suresh","HR",50000),new Employee("Sajay","Manager",60000),new Employee("Abhay","President",70000));
		list.stream().sorted((obj1,obj2)->obj1.salary-obj2.salary).skip(2).limit(1).forEach(emp->{
			System.out.println("Salary: " +emp.salary);
		});
		
		//0,1,1,2,3,5,8,13
		
		IntStream.range(0, 10).boxed().forEach(val->{
			IntStream.rangeClosed(val, val+1).forEach(res->{
				System.out.println(res+1);
			});
		});
	}
}
