package com.zensar.java11;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee{
	@Value("${logging.level.root}")
	private String name;
	private String department;
	private Integer salary;
}

public class TempClass {

	public static void main(String[] args) {
		List<Employee> list = List.of(new Employee("Ramesh","IT",40000),new Employee("Suresh","HR",50000),new Employee("Sajay","Manager",60000),new Employee("Abhay","President",70000));
		list.stream().sorted((obj1,obj2)->obj1.getSalary()-obj2.getSalary()).skip(2).limit(1).forEach(emp->{
			System.out.println("Salary: " +emp.getSalary());
		});
	}
}
