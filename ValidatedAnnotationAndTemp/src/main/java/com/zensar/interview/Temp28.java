package com.zensar.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
class Emp{
	int id;
	String department;
	int salary;
	String name;
	Emp(int id,String name, String department, int salary){
		this.id = id;
		this.department=department;
		this.salary=salary;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", department=" + department + ", salary=" + salary + ", name=" + name + "]";
	}
	
}

public class Temp28 {

//	Group employees by department.
//	For each department, return a list of the top 3 highest-paid employees, sorted by salary descending.
//	The result should be a Map<String, List<Employee>>.
	
	public static void main(String[] args) {
		 List<Emp> employees = Arrays.asList(
		           new Emp(1, "Alice", "HR", 70000),
		           new Emp(2, "Bob", "HR", 80000),
		           new Emp(3, "Charlie", "HR", 75000),
		           new Emp(4, "David", "IT", 90000),
		           new Emp(5, "Eve", "IT", 95000),
		           new Emp(6, "Frank", "IT", 85000),
		           new Emp(7, "Grace", "IT", 95000)
		       );
		 
		 Map<String, List<Emp>> collect = employees.stream().sorted((obj1,obj2)->obj2.getSalary()-obj1.getSalary()).collect(Collectors.groupingBy(ob->ob.department));
		 //System.out.println(collect);
		 collect.entrySet().stream().forEach(lst->{
			 lst.getValue().stream().limit(3).forEach(System.out::println);
		 });
		// System.out.println(collect);
	}

}
