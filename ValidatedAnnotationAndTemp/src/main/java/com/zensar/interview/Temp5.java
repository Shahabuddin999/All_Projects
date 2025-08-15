package com.zensar.interview;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;
import java.util.*;

class Employee {
	String name;
	String address;
	int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", address=" + address + ", id=" + id + "]";
	}
	Employee(String name, String address, int id){
		this.name=name;
		this.address=address;
		this.id=id;
	}
	
}

public class Temp5 {

	public static void main(String[] args) {
		
		Map<String, List<Employee>> map = new LinkedHashMap<>();
		map.put("HR", List.of(new Employee("Raj","Mumbai",1),new Employee("Rajkumar","Delhi",2)));
		map.put("IT", List.of(new Employee("Sanjay","Hyderabad",3),new Employee("Rahu;","Pune",4)));
		
		map.entrySet().stream().filter(entry->entry.getKey().equals("IT")).forEach(entry->{
			System.out.println("Department: "+entry.getKey() +" : "+entry.getValue());
		});
		
		Map<String, String> map1 = Map.of("A", "Apple", "B", "Banana");
		//map1.put("C", "Cherry"); // it won't Works because Map.of() returns immutable/unmodifiable
		System.out.println(map1); 
		
		
		 String str = "Hello";

	        StringBuilder reversed = Stream.of(str.split(""))  // Stream<String>
	            .reduce(new StringBuilder(),                   // Identity (StringBuilder)
	                    (sb, ch) -> sb.insert(0, ch),           // Accumulator: insert at start
	                    (sb1, sb2) -> sb1.insert(0, sb2));      // Combiner: merge in reverse order

	        System.out.println(reversed.toString()); // Output: olleH
	        
	        
	        String result = Stream.of("abc".split("")) // Stream<String>
	                .reduce(
	                    "",                            // Identity (String)
	                    (str1, ch) -> ch + str1        // Combiner: Merge in reverse order
	                );

	        System.out.println(result);
	}

}
