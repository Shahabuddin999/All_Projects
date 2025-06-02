package com.zensar.temp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;  
public class ConcurrentHashMapExample2{  
      
    public static void main(String[] args)   
    {   
    	ConcurrentHashMap<Integer,String> h=new ConcurrentHashMap<>(); // Will work fine
        //HashMap<Integer,String> h=new HashMap<Integer,String>(); // Will throw ConcurrentModificationException Exception
        h.put(100,"X");   
        h.put(101,"Y");   
        h.put(102,"Z"); 
        h.put(11,"x"); 
        //System.out.println(h);
        h.forEach((key,value)->{
        	h.remove(key);
        	h.put(102, "M");
        });
        //System.out.println(h);
        h.entrySet().forEach(key->{
        	h.remove(key.getKey());
        });
        //System.out.println(h);
        
        // Converting String into list with characters 
        List<String> namechars = Arrays.asList("shahhabuddin".toLowerCase().split(""));
        //System.out.println(namechars);
        // Converting List into Map with count of each character 
        Map<String,Integer> countChar = namechars.stream().collect(Collectors.toMap(Function.identity(),initialValue->1,Math::addExact));
        System.out.println("count: "+countChar);
        // Fetching character which appeared maximum times
        Optional<Map.Entry<String, Integer>> entry = countChar.entrySet().stream().max((val1,val2)->val1.getValue()-val2.getValue());
        System.out.println(entry.get().getKey() +" : "+entry.get().getValue());
        // Sorting map according value
        Map<String,Integer> sorted = countChar.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
        		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,LinkedHashMap::new));   
        
        // Getting those charrectors which are appeared more that 1 times
        List<String> dup = namechars.stream().filter(val->Collections.frequency(namechars, val)>1).collect(Collectors.toList());
        System.out.println(namechars+":   dup: "+dup);
        Map<String,Integer> count = namechars.stream().collect(Collectors.toMap(Function.identity(),init->1, Math::addExact));
        System.out.println("counting :"+count);
        
        count = namechars.stream().collect(Collectors.toMap(Function.identity(),init->1, Math::addExact));
        System.out.println("counting :"+count);
        ///////////////////////
        int chunckSize=5;
        Integer[] array = {10,20,30,40,60,70,80,90,100,12,34,45,67,43,65,76,8};
        List<Integer[]> list = new ArrayList<>();
        for(int from=0;from<array.length;from+=chunckSize) {
        	int to = from+chunckSize;
        	list.add(Arrays.copyOfRange(array, from, to>=array.length?array.length:to));
        }
        for(Integer[] val : list) {
        	System.out.print("chuck size : "+chunckSize+" [");
        	for(int index=0; index<val.length;index++) 
        		System.out.print(val[index] +(index<val.length-1?",":""));
        	System.out.println("] Array size:" +val.length);
        }
        
        int x = 10, y = 5;
        
        // Code to swap 'x' and 'y'
        x = x + y; // x now becomes 15
        y = x - y; // y becomes 10
        x = x - y; // x becomes 5
        System.out.println("x="+x+", y=" +y);
        Map<String,Integer> mapping = new LinkedHashMap<>();
        mapping.put("shahab", 1);mapping.put("ansari", 10);mapping.put("koraon", 3);mapping.put("kosfra", 5);
        
        //mapping.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey().reversed()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1, LinkedHashMap::new));
        System.out.println(Arrays.asList(array).stream().min((o1,o2)->o1-o2).get());
        System.out.println(Arrays.asList(array).stream().min(Collections.reverseOrder()).get());
        
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 3);
        map.put("banana", 1);
        map.put("cherry", 2);
        
		Map<String, Integer> sort = map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		  System.out.println(sort);
		  
		  sort = map.entrySet().stream().sorted((entry1,entry2)->entry2.getKey().compareTo(entry1.getKey()))
					.collect(Collectors.toMap(
												entry3->entry3.getKey(), 
												entry4->entry4.getValue(), 
												(e1, e2) -> e1, 
												() -> new LinkedHashMap<>())
											  );
			  System.out.println(sort);
			  
			  
			  Map<String, Integer> duplicateKey = Arrays.asList("shahab","ansari","shahab","nizam","nizam").stream()
					    .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(s -> 1)));
			  System.out.println("dupl : "+duplicateKey);
    
    }    
    
    
}  