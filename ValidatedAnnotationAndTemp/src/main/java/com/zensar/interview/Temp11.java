package com.zensar.interview;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
public class Temp11 {
       public static void main(String[] args) {
        String str = "coodinng";
        Map<String,Long> map = Arrays.stream(str.split(""))
        .sorted((s1,s2)->s2.compareTo(s1))
        .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()));
        // LinkedHashMap::new this maintain insertion order otherwise by default it returns HashMap which does not maintain order
        
        System.out.println(map);
        
         Map<String,Long> map2 = map.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed())
        		 .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a1,a2)->a1,LinkedHashMap::new));
        // (a1,a2)->a1 in case of duplicate key keeps fist value, (a1,a2)->a2 in case of duplicate key keep second value,
        
        System.out.println(map2);
        
        Map<Integer, Integer> mp = new LinkedHashMap<>();
        mp.put(1, 2);
        mp.put(1, 4);
        mp.put(2, 5);
        mp.put(2, 8);
        System.out.println(mp);
        
        /*
         * NOTE-:
         * .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())
         *  in goroupinBy() LinkedHashMap::new is placed in center but in Collectors.toMap() LinkedHashMap::new is placed in last
         * .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a1,a2)->a1,LinkedHashMap::new))
         * 
         * LinkedHashMap::new is used for maintaining insertion order
         */
        
       }
}
