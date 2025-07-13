package com.zensar.java11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8 {
	
	enum SingletonEnum {
	    INSTANCE;

	    public void doSomething() {
	        System.out.println("Doing work...");
	    }
	}
	
	public static void main(String[] args) {
		String input = "My name is Shahabuddin";

     // 1)
		Map<String, Integer> wordLengthMap = Arrays.stream(input.split("\\s+"))
            .collect(Collectors.toMap(
                word -> word,
                word -> word.length(),
                (oldVal, newVal) -> oldVal,  // In case of duplicates, keep original
                LinkedHashMap::new            // Maintain insertion order
            ));

        System.out.println(wordLengthMap);
        // 2)
        wordLengthMap = Arrays.stream(input.split(" "))
                .collect(Collectors.toMap(
                    word -> word,
                    word -> word.length(),
                    (oldVal, newVal) -> oldVal  // In case of duplicates, keep original
                ));

            System.out.println(wordLengthMap);
            //3)
            wordLengthMap = Arrays.stream(input.split(" "))
                    .collect(Collectors.toMap(
                        word -> word,
                        word -> word.length()
                    ));

                System.out.println(wordLengthMap);
                
              System.out.println("=======================================");
              Map<String, Integer> mapSort = new HashMap<>();
    	        mapSort.put("apple", 3);
    	        mapSort.put("cherry", 1);
    	        mapSort.put("banana", 2);
    	        mapSort.put("saumya", 4);
    	        mapSort.put("amarud", 5);
    	        mapSort.put("dunkey", 0);
    	        mapSort.put("rahul", -1);
    	        
    	        Map<String, Integer> sort = mapSort.entrySet().stream().sorted((v1,v2)->v1.getKey().compareTo(v2.getKey()))
    					.collect(Collectors.toMap(
    							v1->v1.getKey(), 
    							v2->v2.getValue(),
    							(v1,v2)->v1,  // In case of duplicates, keep original
    							LinkedHashMap::new  // Maintain insertion order
    							));
    	        System.out.println(sort);
    	        
    	        /*  
    	            if you don't write (v1,v2)->v1 and LinkedHashMap::new then your map will not be sorted because Collectors.toMap() is HashMap by default, which does not maintain any order
    	            So, 
    	            By default, this collects into a HashMap, which:Does not preserve insertion order
					So your sorted keys get "shuffled" again when inserted into the final map.
					✅ ✅ Solution:
					Use a LinkedHashMap in Collectors.toMap() to preserve the sorted order:
    	         */
    	        
    	        
    	        /*
    	            how to improve database performance for long running queries?
    	            --------------------------------------------------------------
    	            Use in Spring Data JPA or Spring Boot

					Pageable pageable = PageRequest.of(2, 50); // page 3 (0-based), size 50
					productRepository.findAll(pageable);
					
					This generates:
					
					SELECT * FROM products ORDER BY id LIMIT 50 OFFSET 100;
					
					⚠️ Performance Warning for Large OFFSETs
					OFFSET is slow on large values — DB engine has to scan and skip all prior rows.
					
					For large pages, use seek pagination (keyset-based) for better performance:
					
					SELECT * FROM products
					WHERE id > last_seen_id
					ORDER BY id
					LIMIT 50;

    	         */
    	        
    	        System.out.println("=====================Enum is best for Singleton===========================");
    	        
    	        SingletonEnum singleton1 = SingletonEnum.INSTANCE;
    	        SingletonEnum singleton2 = SingletonEnum.INSTANCE;

    	        singleton1.doSomething();

    	        System.out.println("Are both instances same? " + (singleton1 == singleton2));
    	        
    	        
	}
}
