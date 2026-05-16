package com.zensar.interview;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tem35 {

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
		 
		 			List<Emp> collect = employees.stream()
				 	.sorted((emp1,emp2)->emp2.getSalary()-emp1.getSalary())
				 	.collect(Collectors.groupingBy(emp->emp.getDepartment()))
				 	.entrySet()
				 	.stream()
				 	.map(entry->entry.getValue().stream())
				 	.flatMap(m->m).limit(2)
				 	.collect(Collectors.toList());
			
		 //collect.forEach(System.out::println);
		 // Capitalize string
		 String name = "Brij kishor Koraon allahabad";
		 Map<String, String> collect2 = List.of(name.split(" ")).stream().map(str->{
			 Optional<String> s = str.chars().mapToObj(c->String.valueOf((char)(c>=97 && c<=(97+25)? c-32:c+32))).findFirst();
			 String calc = s.get()+str.substring(1);
			 long count2 = calc.chars().filter(ch->"aeiou".contains(String.valueOf((char)ch).toLowerCase())).count();
			 //long count = List.of(calc.split("")).stream().filter(ch->"aeiou".contains(ch.toLowerCase())).count();
			 return calc+" "+count2;
			 
		 }).collect(Collectors.toMap(key->key.split(" ")[0], value->value.split(" ")[1]));
		 System.out.println(collect2);
		 
		 
		 int num = 213657;
		 List<Integer> collect3 = IntStream.rangeClosed(0, 9).filter(d->!(num+"").contains(d+"")).boxed().collect(Collectors.toList());
		 System.out.println(collect3);
		 
		 List<String> list = List.of("shah","ansar","koraon");
		 Map<String, Integer> collect4 = list.stream().sorted((e1,e2)->e2.compareTo(e1))
				 .collect(Collectors.toMap(Function.identity(), String::length,(e1,e2)->e1, LinkedHashMap::new));
		 System.out.println(collect4);
		 
		 LinkedHashMap<String, Integer> collect5 = collect4.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
		 .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1, LinkedHashMap::new));
		 System.out.println(collect5);
		 
		 String[] lst = list.toArray(String[]::new);
		 System.out.println(lst[0]);
		 
		 String converted = list.stream().collect(Collectors.joining(" "));
		 System.out.println(converted);
		 
		 char[] ch = converted.toCharArray();
		 String s = new String(ch);
		 System.out.println(ch);
		 System.out.println(s);
		 
		 System.out.println("======================");
		 
		 List<Integer> listing = List.of();
		 
		//Integer sum = listing.stream().reduce((a,b)->a+b).get(); // this will throw NoSuchElementException exception without Identifier
		 //System.out.println(sum);
		 
		 
		 Integer sum = listing.stream()
		               .reduce(0,(a,b)->a+b);
		 System.out.println(sum);
		
		 //Optional<Object> off = Optional.of(null); // null is not allowed in Optional.of(null) 
		 
		 Optional<Object> of = Optional.ofNullable(null);
		 System.out.println(of.orElse("This is empty"));
		 System.out.println(of.orElseThrow());
    }

}
