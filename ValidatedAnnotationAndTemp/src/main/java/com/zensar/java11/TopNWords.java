package com.zensar.java11;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class TopNWords {
    public static void main(String[] args) {
        String paragraph = "Java is great. Java is object-oriented. Java is fast fast and Java is powerful powerful!";
        int topN = 3;

        // Step 1: Normalize (lowercase + remove punctuation)
        String cleaned = paragraph.toLowerCase().replaceAll("[^a-z0-9\\s]", "");

        // Step 2: Split into words and count frequencies using Java 8 Stream API
        Map<String, Long> freqMap = Arrays.stream(cleaned.split("\\s+"))
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        // Step 3: Sort and limit top N
        freqMap.entrySet().stream()
            .sorted((e1, e2) -> {
                int cmp = e2.getValue().compareTo(e1.getValue()); // Descending by count
                return (cmp != 0) ? cmp : e1.getKey().compareTo(e2.getKey()); // Tie-breaker: ascending by word
            })
            .limit(topN)
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        
        System.out.println("-----------------------------------------------");
        
        Arrays.stream(paragraph.replaceAll("[^a-zA-Z ]", "")
        	  .split(" "))
        	  .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
        	  .entrySet()
        	  .stream()
        	  .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        	  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2)->e1, LinkedHashMap::new))
        	  .entrySet()
        	  .stream()
        	  .limit(topN)
        	  .forEach(entry->{
        			System.out.println(entry.getKey() +" : "+entry.getValue());
        		});
         
        
        System.out.println("===================");
        Arrays.stream(paragraph.replaceAll("[^a-zA-Z ]", "").split(" "))
        .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
        .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .limit(topN)
        .forEach(entry->{
        	System.out.println(entry.getKey() +" : "+entry.getValue());
        });
        
    }
}
