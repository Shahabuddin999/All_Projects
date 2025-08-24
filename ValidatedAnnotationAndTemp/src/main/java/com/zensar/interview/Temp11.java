package com.zensar.interview;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
public class Temp11 {
       public static void main(String[] args) {
        String str = "coodinng";
        Map<String,Long> map = Arrays.stream(str.split(""))
        .sorted((s1,s2)->s2.compareTo(s1))
        .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        
         Map<String,Long> map2 = map.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed())
        		 .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a1,a2)->a1,LinkedHashMap::new));
        System.out.println(map);
        System.out.println(map2);
    }
}
