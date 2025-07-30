package com.zensar.temp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee{
	Integer id;
	String name;
	Integer salary;
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		Employee other = (Employee) obj;
		return Objects.equals(name, other.name);
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee_2{
	Integer id;
	String name;
	double salary;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee_2 other = (Employee_2) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, salary);
	}
}
public class Tempo {

	public static void main(String[] args) {
		List<Employee> list = List.of(new Employee(1, "Alice", 30000), new Employee(2, "Bob", 250000), new Employee(3, "Alice", 350000));
		List<Employee> collect = list.stream().sorted((obj1,obj2)->obj2.getSalary()-obj1.getSalary()).distinct().collect(Collectors.toList());
		System.out.println(collect);
		
		Map<Employee_2,String> map = new LinkedHashMap<>();
		map.put(new Employee_2(10,"Shahab",5000), "Shahab");
		map.put(new Employee_2(11,"Nizam",6000), "Nizammm");
		map.put(new Employee_2(12,"Kalam",7000), "Kalam");
		map.put(new Employee_2(12,"Kalam",7000), "Kalam");
		map.put(new Employee_2(12,"Kalam",7000), "Kalam");
		
		String obj = map.get(new Employee_2(12,"Kalam",7000));
		System.out.println(obj);
		
		map.entrySet().stream().forEach(System.out::println);
	}
}
