package com.zensar.java11;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupByFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is great Java is object oriented Java is fast fast and Java is powerful powerful";

           Map<String, Long> map = Arrays.stream(paragraph.split(" "))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(
                    Function.identity(),                 // word as key
                    Collectors.counting()                // count how many times it appears
                ));
                
           System.out.println(map);
           
           		Map<Long, List<String>> collect = map.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                    Map.Entry::getValue,               // group by frequency (count)
                    Collectors.mapping(Map.Entry::getKey, Collectors.toList())  // collect words with same count
                ));

       System.out.println(collect);
       
       System.out.println("=====================================================\n");
       List<String> collect2 = Arrays.stream(paragraph.split(" ")).collect(Collectors.mapping(Function.identity(), Collectors.toList()));
       System.out.println(collect2);
       
       Integer []arr = {10,20,30};
       long count = Arrays.stream(arr).count(); // count() method works on Stream of Object and returns long
       System.out.println(count);
       
       // mapToInt(), mapToDouble(), mapToLong() converts wrapper to Objects into primitive stream like IntStream,DoubleStream,LongStream
       Integer sum = Arrays.stream(arr)
    		   		.mapToInt(Integer::intValue) //.mapToInt(val->Integer.parseInt(val+"")) both are equal
    		   		.sum(); // sum() method works for IntStream(int),LongStream(),DoubleStream() and returns int/Integer thats why I did Integer.parseInt(). Stream<Integer> is a stream of objects, while IntStream is a stream of primitives (int) — and only IntStream has .sum() method.
       System.out.println(sum);
       
       int []arrayInt = {10,20,30};
       int addition = Arrays.stream(arrayInt).sum();
       System.out.println(addition);
       
       Integer []array = {10,20,30};
       Optional<Integer> reduce = Arrays.stream(array).reduce((a,b)-> a+b);
       System.out.println(!reduce.isEmpty()?reduce.get():"its empty");
       
       int []arrayIntValu = {10,20,30};
       int addValue = Arrays.stream(arrayIntValu).reduce(0,(a,b)-> a+b);// Arrays.stream(int[]) returns an IntStream — specifically designed for primitive int arrays. IntStream has direct support for operations like .reduce() with int operands.
       System.out.println(addValue);
       
       // Note: reduce() can add value of Integer as well as int, but sum() can add only IntStream means int only because sum() is available in IntStream only
    
       String str[]= {"a","b","c"};
       String strSum = Arrays.stream(str).reduce("",(a,b)->b+a);
       System.out.println(strSum);
       
       // char charStr[]= {'a','b','c','d'};
       Character charStr[]= {'a','b','c','d'};
       String charSum = Arrays.stream(charStr).map(ch->String.valueOf(ch)).reduce("",(a,b)->b+a); // Arrays.stream(char[]) does not exist because Java does not have CharStream like IntStream, DoubleStream, or LongStream.
       System.out.println(charSum);
       
       
       Map<String, List<String>> grouped = List.of(paragraph.split(" ")).stream()
       		    .collect(Collectors.groupingBy(Function.identity()));
       
       System.out.println(grouped);
       
       List<Integer> list = IntStream.of(1, 2, 3, 4, 5) // IntStream.of(1, 2, 3, 4, 5) containing primitives values only
               .mapToObj(val->Integer.valueOf(val)) // or i -> i // mapToObj() Converts primitives to any object type, here converting int to Integer
               .collect(Collectors.toList());
       	System.out.println(list);
       	
       	// .boxed() converts primitives value into its respective wrappers class
       	// mapToObj() converts primitives value into any object like int->Integer, int->String like int+" hello", char->Character
       	// mapToInt(), mapToDouble(), mapToLong() converts wrappers class object into its respective primitive value you can say IntStream,DoubleStream, LongStream
       	
       	Arrays.stream("hello brother".split(" ")).forEach(string->{
       		String reduce2 = Arrays.stream(string.split("")).reduce("",(a,b)->b+a);
       		System.out.println(reduce2);
       	});
       	
    	String reduce2 = Arrays.stream("hello brother".split("")).reduce("",(a,b)->b+a);
    	System.out.println(reduce2);
    	
//    	String input = "hello brother";

//        String reversed = Arrays.stream(input.split(""))  // split into characters
//        		.reduce(new StringBuilder(),
//        	            (sb, ch) -> sb.insert(0, ch),
//        	            (sb1, sb2) -> sb1.append(sb2))
//        	    .toString();
//
//        System.out.println(reversed);
    	
    }
}
