package com.zensar.temp;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class FlatMapExammple {
  public static void main(String[] args) {
    List<Integer> list1 = Arrays.asList(1,2,3);
    List<Integer> list2 = Arrays.asList(4,5,6);
    List<Integer> list3 = Arrays.asList(7,8,9);

    List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

    List<Integer> listOfAllIntegers = listOfLists.stream()
            .flatMap(x -> x.stream())
            .collect(Collectors.toList());

    System.out.println(listOfAllIntegers);    //[1, 2, 3, 4, 5, 6, 7, 8, 9]
    
    List<String> names = Arrays.asList("Shahabudin Ansari","Shahabudin Ansari", "Seraj khan", "Ahmad bhai", "Alam bhai");
    List<String> uppercaseNames = names.stream()
                                       .map(value->value.toUpperCase())
                                       .collect(Collectors.toList());
    System.out.println(uppercaseNames);
    
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    int sum = numbers.stream().reduce(0,(v1,v2)->v1+v2);
    System.out.println(sum);
    
    numbers.stream()
    .peek(num -> System.out.println("Will filter number: " + num))
    .filter(x -> x > 3)
    .forEach(System.out::println);
    
    Map<Character, List<String>> groupedByInitial = names.stream()
            .collect(Collectors.groupingBy(name -> name.charAt(0)));
    System.out.println(groupedByInitial);
    
    names.stream().map(val->val).forEach(System.out::println);
    
    Map<Integer,String> map = new LinkedHashMap<>();
    map.put(2, "Shahabuddin");
    map.put(1, "Kalamuddin");
    map.put(4, "Parwej");
    map.put(3, "Naziya");
    
    Map<Integer,String> sortedMap = map.entrySet().stream().sorted(Map.Entry.<Integer,String>comparingByKey().reversed()).
    		collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1, LinkedHashMap::new));
    System.out.println(sortedMap);
    
    Map<String, Integer> count = names.stream().collect(Collectors.toMap(Function.identity(),initial->1,Math::addExact));
    System.out.println(count);
    
    List<String> dupli = names.stream().filter(val->Collections.frequency(names, val)>1).collect(Collectors.toList());
    System.out.println(dupli);
  }
}