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
		           new Emp(5, "Eve", "IT", 93000),
		           new Emp(6, "Frank", "IT", 85000),
		           new Emp(7, "Grace", "IT", 95000)
		       );
		 
		 Map<String, List<Emp>> collect = employees.stream().sorted((obj1,obj2)->obj2.getSalary()-obj1.getSalary()).collect(Collectors.groupingBy(ob->ob.department));
		 System.out.println(collect);
		 collect.entrySet().stream().forEach(lst->{
			 lst.getValue().stream().limit(2).forEach(System.out::println);
		 });
		// System.out.println(collect);
		 
		 System.out.println("--------------");
		 employees.stream()
		 	.sorted((emp1,emp2)->emp2.getSalary()-emp1.getSalary())
		 	.collect(Collectors.groupingBy(emp->emp.getDepartment()))
		 	.entrySet()
		 	.stream()
		 	.map(entry->entry.getValue().subList(0, 2))
		 	.forEach(System.out::println);
		 
		 System.out.println("--------------");
		 employees.stream()
		 	.sorted((emp1,emp2)->emp2.getSalary()-emp1.getSalary())
		 	.collect(Collectors.groupingBy(emp->emp.getDepartment()))
		 	.entrySet()
		 	.stream()
		 	.forEach(entry->{
		 		entry.getValue().stream().limit(2).forEach(System.out::println);
		 		
		 	});
		 
		 System.out.println("--------------");
		 Integer j[] = new Integer[1];j[0]=1; Integer k[] = new Integer[1];k[0]=1;
		 employees.stream()
		 	.sorted((emp1,emp2)->emp2.getSalary()-emp1.getSalary())
		 	.collect(Collectors.groupingBy(emp->emp.getDepartment()))
		 	.entrySet()
		 	.stream()
		 	.map(entry->entry.getValue().stream().limit(2)) // returning stream of stream means nested stream
		 	.forEach(data->{
		 		System.out.println("Stream: "+k[0]++);
		 		data.forEach(emp->System.out.println(emp+":"+j[0]++));
		 		System.out.println();
		 	});
		 System.out.println("--------------");
		 List<Emp> collect2 = employees.stream()
		 	.sorted((emp1,emp2)->emp2.getSalary()-emp1.getSalary())
		 	.collect(Collectors.groupingBy(emp->emp.getDepartment()))
		 	.entrySet()
		 	.stream()
		 	.flatMap(entry->entry.getValue().stream().limit(2))
		 	.collect(Collectors.toList());
		 System.out.println(collect2);

		 System.out.println("--------------");
		 Integer i[] = new Integer[1];i[0]=1;
		 employees.stream()
		 	.sorted((emp1,emp2)->emp2.getSalary()-emp1.getSalary())
		 	.collect(Collectors.groupingBy(emp->emp.getDepartment()))
		 	.entrySet()
		 	.stream()
		 	.flatMap(stm->stm.getValue().stream().limit(2))
		 	.forEach(emp-> System.out.println(emp+":"+i[0]++));
		 	
		 	//.flatMap(entry->entry.getValue().stream().limit(2)) // returning stream only because its flating the stream.
		 	//.forEach(data->System.out.println(data));
	}

}
