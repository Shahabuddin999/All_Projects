package com.zensar.interview;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
class MyEmployee{
	String name;
	Integer salary;
	String dep;
	MyEmployee(String name, Integer salary, String dep){
		this.name =name;
		this.dep = dep;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", dep=" + dep + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(dep);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyEmployee other = (MyEmployee) obj;
		return Objects.equals(dep, other.dep);
	}
	
}
public class Temp6 {

	public static void main(String[] args) {
		List<MyEmployee> list = List.of(new MyEmployee("Shahab",10000,"IT"),new MyEmployee("Abhay",20000,"IT"),new MyEmployee("Saumya",50000,"HR"),new MyEmployee("Aditi",30000,"HR"));
		list.stream().sorted((emp1,emp2)->emp2.getSalary()-emp1.getSalary()).distinct().forEach(System.out::println);
		
		String str ="abcabc";
		String newStr = Arrays.stream(str.split("")).distinct().collect(Collectors.joining());
		System.out.println(newStr);
		
		List<String> ls = List.of("aa","cc","bb");
		System.out.println(String.join(" ",ls));
	}
}