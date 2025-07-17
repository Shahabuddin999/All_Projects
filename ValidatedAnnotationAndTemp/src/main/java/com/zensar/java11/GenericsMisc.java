package com.zensar.java11;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zensar.temp.Transaction;
public class GenericsMisc {
	private static Logger logger = LoggerFactory.getLogger(GenericsMisc.class); 
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
            "AANAYA", "AAAAA", "ATHAH", "AAVCCANA", "ATACK", "AJAAYA", "AKIRA", "AARAV", "AJAYYY"
        );

        
        "ABC".chars().forEach(System.out::println);
        System.out.println("converting into char: "+(char)65);
        
        System.out.println("---------------------------------------------------------");

        words.stream().filter(val->val.startsWith("AA")).collect(Collectors.toList())
        .stream().forEach(val->{
        	long count = val.chars()
        			.filter(ch->Character.isLetter(ch))
        			//.filter(Character::isLetter) // This line and just above line is same
        			.filter(ch->!"AEIOU".contains(String.valueOf((char)ch)))
        			.count();
        	System.out.println(val+" : "+count);
        });
        
        System.out.println("---------------------------------------------------------");
        
        words.stream().filter(data->data.startsWith("AA")).forEach(data->{
        	long count = data.chars().filter(value-> !"AEIOU".contains(String.valueOf((char)value))).count();
        	System.out.println(data +" : "+count);
        });
        
        System.out.println("---------------------------------------------------------");
        
        words.stream().filter(data->data.startsWith("AA")).forEach(data->{
        	long count = data.chars().filter(value-> !"AEIOU".contains(String.valueOf((char)value))).count();
        	System.out.println(data +" : "+count);
        });
        System.out.println("---------------------------------------------------------");
        Map<String, Long> collect = words.stream().filter(data->data.startsWith("AA")).
        collect(Collectors.groupingBy(val->val,Collectors.summingLong(data->{
        	long count = data.chars().filter(value-> !"AEIOU".contains(String.valueOf((char)value))).count();
        	return count;
        })));
        
        System.out.println(collect);
        
     // Getting prime number
 		IntStream.rangeClosed(1,100).forEach(value->{
 			boolean noneMatch = IntStream.rangeClosed(2,(int)Math.sqrt(value)).noneMatch(val->value%val==0);
 			if(noneMatch)
 				System.out.println("Prime : "+value);
 		});
 		
 		List<List<Integer>> list = List.of(List.of(10,3,6,1,2),List.of(8,9,7),List.of(30,40));
		List<Integer> collectList = list.stream()
				.flatMap(list1->list1.stream())
				//.sorted(Comparator.naturalOrder()) // Comparator.naturalOrder() Ascending order // Comparator.reverseOrder() Descending order
				//.sorted() // Ascending order 
				.sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		System.out.println("Sorted : "+collectList);
		
		List<Transaction> trasactionPlus = Transaction.getTrasactionPlus();
		trasactionPlus.stream().forEach(obj->{
			System.out.println(obj.getDate().getYear());
		});
		
		int[] i={1,2,3,3,4,4,4,5,5,2};
		Arrays.stream(i).distinct().forEach(System.out::println);
		
		String []stringArr = {"shahab","ansari",null,"","kalam"};
		Arrays.stream(stringArr)
		.filter(Objects::nonNull) // .filter(obj->obj!=null && !obj.isEmpty())
		.filter(obj->!obj.isEmpty())
		//.sorted(Comparator.naturalOrder())
		.sorted(Collections.reverseOrder())
		.forEach(System.out::println);
		
		Set<Integer> collect2 = IntStream.rangeClosed(1, 20).filter(isPrime->{
			return IntStream.rangeClosed(2, (int) Math.sqrt(isPrime)).noneMatch(value->isPrime%value==0);
		}).boxed().collect(Collectors.toSet());
		logger.info("Prime: "+collect2);
		
		//System.out.println(Math.pow(4, 3));
		System.out.println("==========================");
		IntStream.rangeClosed(2, 160).filter(val->{
			int sum = String.valueOf(val).chars()
			.map(data->Character.getNumericValue(data))
			.map(digit-> (int)Math.pow(digit, 3)).sum();
			return sum == val;
		}).forEach(System.out::println);
		
		System.out.println("==========================");
		IntStream.of(1, 2, 3, 4)
        .boxed() // IntStream → Stream<Integer> // if you don't boxed() it then you can not return from .map() different input and different output. because boxed() changing IntStream intoStream<Integer>, then map can receive any data type and return any different data type 
        .map(val -> "Hello") // Each Integer is replaced by "Hello"
        .forEach(System.out::println);
		
		IntStream.of(1, 2, 3, 4)
        //.boxed() // IntStream → Stream<Integer> // if you don't boxed() it then you can not return from .map() different input and different output. because boxed() changing IntStream intoStream<Integer>, then map can receive any data type and return any different data type 
        .mapToObj(val -> "Hello") // Each Integer is replaced by "Hello"
        .forEach(System.out::println);
    }
}
