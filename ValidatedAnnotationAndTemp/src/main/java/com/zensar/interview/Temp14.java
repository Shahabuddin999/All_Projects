package com.zensar.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Temp14 {

	Optional<List<String>> getList(List<String> list){
		 
		if(!list.isEmpty()) {
		   List<String> collect = list.stream()
				   .filter(str-> str!=null && !str.isBlank())
				   .map(str->{
					   str = str.trim();
					   str = str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
					   return str;
				   }).distinct()
				   .collect(Collectors.toList());
		   return Optional.of(collect);
		}
		else {
			return Optional.empty();
		}
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		   list.add(" alice ");
		   list.add(null);
		   list.add(" alice ");
		   list.add("BOB");
		   list.add("Alice");
		   list.add("  john");
		   list.add("JOHN ");
		   list.add("mary");
		  Optional<List<String>> li = new Temp14().getList(list);
		  Optional<List<String>> li2 = new Temp14().getList(new ArrayList());
		  
		  System.out.println(li.get());
		  System.out.println(li2);
		  
	}

}
