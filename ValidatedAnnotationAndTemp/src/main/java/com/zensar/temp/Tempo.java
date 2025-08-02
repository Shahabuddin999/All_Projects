package com.zensar.temp;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	
}

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
	@Override
	public String toString() {
		return "Employee_2 [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	
}


@AllArgsConstructor
@NoArgsConstructor
class Employee_3{
	Integer id;
	String name;
	double salary;
	@Override
	public String toString() {
		return "Employee_3 [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
}

@AllArgsConstructor
@NoArgsConstructor
class Employee_4{
	Integer id;
	String name;
	double salary;
	@Override
	public String toString() {
		return "Employee_3 [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, salary);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee_4 other = (Employee_4) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}
}

public class Tempo {

	public static void main(String[] args) {
		List<Employee> list = List.of(new Employee(1, "Alice", 30000), new Employee(2, "Bob", 250000), new Employee(3, "Alice", 350000));
		List<Employee> collect = list.stream().sorted((obj1,obj2)->obj2.salary-obj1.salary).distinct().collect(Collectors.toList());
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
		
		System.out.println("----------------------------------------------");
		Set<Employee_3> set = Set.of(new Employee_3(1, "Alice", 30000),
				new Employee_3(1, "Alice", 30000), // if override hashCode() and equals() of Employee_3 class then this will throw IllegalArgumentException
				new Employee_3(2, "Bob", 250000), 
				new Employee_3(3, "Alice", 350000));
		System.out.println(set);
		
		//Set<Integer> set2 = Set.of(1,1,2,3); This will not work throw exception : java.lang.IllegalArgumentException because its immutable and does not allow duplicate in Set.of() method
		//System.out.println(set2);
		Set<Integer> set3 = new HashSet<Integer>(); // This will work because its mutable read below description for more deeply info
		set3.add(1);set3.add(1);
		System.out.println(set3);
		
		/*
		Set.of(...) is immutable and does not allow duplicates (based on equals()).
		If duplicates are inserted, it throws IllegalArgumentException.
		Use HashSet<> if you want mutable behavior and need to confirm deduplication(to remove duplicate) by behavior:
		Conclusion:
		✔️ Set.of() does call equals() and hashCode().
		❌ But it throws error on duplicate, instead of silently ignoring like HashSet.
		✅ To safely remove duplicates based on .equals(), prefer and use new HashSet<>(...).	
		
		Lombok's @Data includes: @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
		 */
		System.out.println("----------------------------------------------");
		Set<Employee_4> set1 = new HashSet<>();
		set1.add(new Employee_4(1, "Alice", 30000));
		set1.add(new Employee_4(1, "Alice", 30000));
		set1.add(new Employee_4(2, "Bob", 250000));
		set1.add(new Employee_4(3, "Alice", 350000));
		set1.stream().forEach(System.out::println);
		
		/*
		| Operation                | Calls `equals()`? | Calls `hashCode()`? | Notes                   |
		| ------------------------ | -------------------| --------------------| -----------------------|
		| `List.contains(obj)`     | ✅ Yes             | ❌ No                | Only equals()          |
		| `Set.add(obj)`           | ✅ Yes             | ✅ Yes               | Prevents duplicates    |
		| `Map.get(key)`           | ✅ Yes (key)       | ✅ Yes (key)         | For key lookup         |
		| `Map.containsKey(key)`   | ✅ Yes (key)       | ✅ Yes (key)         | Uses equals + hashCode |
		| `Map.containsValue(val)` | ✅ Yes (value)     | ❌ No                | Only equals()          |
		
		
		What happens under the hood?
		When you put() a key-value pair in a HashMap, Java internally:
		
		🔢 Step-by-step:
		Calls hashCode() on the key
		→ To find the bucket/index where this entry might go.
		
		Checks if a key already exists in that bucket
		→ It compares keys using equals() to check for duplicates.
		
		Based on that:
		
		If no matching key → new key-value pair is added.
		
		If key already exists (equals returns true) → the old value is replaced.
		
		✅ So, map.put(k, v):
		Method	Called?	Why?
		hashCode()	✅ Yes	To find bucket location
		equals()	✅ Yes	To check if key already exists

		 */

	}
}
