package com.zensar.java11;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zensar.temp.Transaction;
import com.zensar.temp.TransactionPlus;

public class GroupingBy {
	public static void main(String[] args) {
		List<Transaction> transactions = Transaction.getTrasaction();

		Map<LocalDate, Double> sumByDay = transactions.stream()
										.collect(Collectors.groupingBy(Transaction::getDate,Collectors.summingDouble(Transaction::getAmount)));
		
		sumByDay.forEach((date, total) -> System.out.println(date + " : " + total));
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n\n");
		
		
		
		
		
		sumByDay = transactions.stream().collect(
		Collectors.groupingBy(val -> val.getDate(), Collectors.summingDouble(value -> value.getAmount())));
		sumByDay.forEach((date, total) -> System.out.println(date + " : " + total));
		
		Map<String, Double> sumByCategory = transactions.stream().collect(
				Collectors.groupingBy(val -> val.getCategory(), Collectors.summingDouble(value -> value.getAmount())));

		sumByCategory.forEach((category, total) -> System.out.println(category + " :: " + total));
		
		
		Map<LocalDate, Map<String, Double>> result = transactions.stream()
			    		.collect(Collectors.groupingBy(val -> val.getDate(),
					        Collectors.groupingBy(value->value.getCategory(),
					            Collectors.summingDouble(amount->amount.getAmount())
			        )
			    ));
		result.forEach((date, total) -> System.out.println(date + " ::: " + total));
		
		Map<String, List<Transaction>> map=  transactions.stream().collect(Collectors.groupingBy(val->val.getCategory()));
		System.out.println("By Category : "+map);
		
		List<TransactionPlus> transactionsPlus = Arrays.asList(
				new TransactionPlus(LocalDate.of(2023, 5, 28), 100.0, "bus", "Shahabuddin", "koraon", "1234567"),
				new TransactionPlus(LocalDate.of(2023, 5, 28), 150.0, "car", "Shahabuddin", "koraon", "1234567"),
				new TransactionPlus(LocalDate.of(2023, 5, 28), 140.0, "car", "Nizam", "Allahabad", "123456789"),
				new TransactionPlus(LocalDate.of(2023, 5, 29), 200.0, "bike", "Kalam", "koraon", "123456732"),
				new TransactionPlus(LocalDate.of(2023, 5, 29), 50.0, "bike", "Kalam", "koraon", "123456721"),
				new TransactionPlus(LocalDate.of(2023, 5, 30), 300.0, "cycle", "Parvej", "koraon", "123456711")
				);
		
		Map<LocalDate, Map<String, Map<String, Double>>> output  = transactionsPlus.stream()
				.collect(Collectors.groupingBy(TransactionPlus::getDate, // is equals to key->key.getDate()
					Collectors.groupingBy(TransactionPlus::getName, 
						Collectors.groupingBy(TransactionPlus::getAddress,
							Collectors.summingDouble(TransactionPlus::getAmount)))));  //    Map<Date, Map<String, Map<String,Double>>
		
		System.out.println("Output :::: "+output);
		
		
		 Map<String, Integer> duplicateKey = Arrays.asList("shahab","ansari","shahab","nizam","nizam").stream()
				    .collect(Collectors.groupingBy(value->value, Collectors.summingInt(s -> 1)));
		 System.out.println("counting :"+duplicateKey);
		 
	        Map<String, Integer> mapSort = new HashMap<>();
	        mapSort.put("apple", 3);
	        mapSort.put("cherry", 1);
	        mapSort.put("banana", 2);
	        
			Map<String, Integer> sort;
			sort = mapSort.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey().reversed())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			  System.out.println("Sorted Decending order: "+sort);
			
			  sort = mapSort.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
				  System.out.println("Sorted Asending order: "+sort);
				  
		  sort = mapSort.entrySet().stream().sorted((entry1,entry2)->entry2.getKey().compareTo(entry1.getKey()))
					.collect(Collectors.toMap(
												entry3->entry3.getKey(), 
												entry4->entry4.getValue(), 
												(e1, e2) -> e1, 
												() -> new LinkedHashMap<>())
											  );
			System.out.println(sort);
				  
			sort = mapSort.entrySet().stream().sorted((e1,e2)->e1.getKey().compareTo(e2.getKey()))
					.collect(Collectors.toMap(e1->e1.getKey(), e2->e2.getValue(),(e1,e2)->e1,()->new LinkedHashMap<>()));
			System.out.println("Sort : "+sort);
			
			List<String> list = Arrays.asList("Shahahbuddin","Shahahbuddin","Shahahbuddin","Ansari","Sangram","Sangram");
			Map<String,Integer> mapping = list.stream().collect(Collectors.groupingBy(v->v,Collectors.summingInt(e->1)));
			System.out.println(mapping);
		
			mapSort = new HashMap<>();
	        mapSort.put("apple", 3);
	        mapSort.put("cherry", 1);
	        mapSort.put("banana", 2);
	        mapSort.put("saumya", 4);
	        mapSort.put("amarud", 5);
	        mapSort.put("dunkey", 0);
	        mapSort.put("rahul", -1);
	        
			sort = mapSort.entrySet().stream().sorted((v1,v2)->v1.getKey().compareTo(v2.getKey()))
					.collect(Collectors.toMap(
							v1->v1.getKey(), 
							v2->v2.getValue(),
							(v1,v2)->v1,
							LinkedHashMap::new
							));
			System.out.println("Sorted :::::::: "+sort);
			
			Map<String, Integer> counter = list.stream().collect(Collectors.groupingBy(v->v,Collectors.summingInt(v->1)));
			System.out.println(counter);
			Map<LocalDate,Map<String,Double>> m = transactionsPlus.stream().collect(Collectors.groupingBy(v1->v1.getDate(),Collectors.groupingBy(v2->v2.getName(),Collectors.summingDouble(v3->v3.getAmount()))));
			System.out.println(m);
			List<String> counting = list.stream().filter(v1->Collections.frequency(list, v1)>1).collect(Collectors.toList());
			System.out.println(counting);
			Map<String,Integer> c =list.stream().filter(v1->Collections.frequency(list, v1)>1).collect(Collectors.groupingBy(v2->v2,Collectors.summingInt(v3->1)));
			System.out.println(c);
			
			String []arr = List.of("dd","bss","aa").stream().sorted((v1,v2)->v1.compareTo(v2)).collect(Collectors.toList()).toArray(String[]::new);
			for(String val : arr)
				System.out.println(val);
			
			List<String> listing =  Arrays.asList("shahab","shahab","ansari","shahabuddin","nizamuddin","nizam");
			Map<Integer, List<String>> collect = listing.stream().collect(Collectors.groupingBy(str->str.length())); // this line and below line is same because if you dont use ,Collectors.toList() internaly its added by compiler like collect(Collectors.groupingBy(str->str.length(),Collectors.toList()));
			// you can use Function.identity() because it also return input like str->str means  str->str and Function.identity() are equals
			System.out.println(collect);
			Map<Integer, List<String>> collect1 = listing.stream().collect(Collectors.groupingBy(str->str.length(),Collectors.toList()));
			System.out.println("Grouped by length: "+collect1);
			
			Map<String, Long> collect2 = listing.stream().collect(Collectors.groupingBy(str->str, Collectors.counting()));
			System.out.println(collect2);
			//String str = String.join(",", listing);
			//String[] array = listing.toArray(String[]::new);
			//System.out.println(str);
			//Collections.frequency(list, String/int any thing);
			String array = "java is best java is too good java good good good";
			Map<String, Long> collect3 = Arrays.stream(array.split(" "))
					.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
					.entrySet().stream().filter(entry->entry.getValue()>1).collect(Collectors.toMap(v1->v1.getKey(),v2->v2.getValue()));
			System.out.println(collect3);
			
			Integer []intArray = {10,18,20,13,21,21,21,12,17,21,21,18};
			Map<Integer, Long> collect4 = Arrays.stream(intArray).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
			.entrySet().stream().filter(val->val.getValue()>1).collect(Collectors.toMap(key->key.getKey(), value->value.getValue()));
			System.out.println("Map: "+collect4);
			
			System.out.println("-------");
			list.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
			System.out.println("-------");
			list.stream().sorted().forEach(System.out::println);
			
	}
}
