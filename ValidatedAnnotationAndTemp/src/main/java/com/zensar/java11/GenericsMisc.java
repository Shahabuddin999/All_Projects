package com.zensar.java11;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.zensar.temp.Transaction;

public class GenericsMisc {

    public static void main(String[] args) {
        List<String> words = Arrays.asList(
            "AANAYA", "ATHAH", "AAVCCANA", "ATACK", "AJAAYA", "AKIRA", "AARAV", "AJAYYY"
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
				.sorted(Comparator.naturalOrder()) // Comparator.naturalOrder() Ascending order // Comparator.reverseOrder() Descending order
				.collect(Collectors.toList());
		System.out.println(collectList);
		
		
		
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
		.sorted(Comparator.naturalOrder())
		.forEach(System.out::println);
    }
}
