package com.zensar.interview;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Temp7 {

	public static void main(String[] args) {
		String name = "My Name is Shaha";
		
		Arrays.stream(name.split(" ")).forEach(str->{
			String reversed = str.chars().filter(Character::isLetter).mapToObj(ch->String.valueOf((char)ch)).reduce("",(a,b)->b+a);
			System.out.println(reversed);
		});
        
        String reverse = Arrays.stream(name.split(" ")).map(str->str.chars()
		        								   .filter(Character::isLetter)
		        								   .mapToObj(ch->String.valueOf((char)ch))
		        								   .reduce("",(a,b)->b+a)) // reverse each word
        								.collect(Collectors.joining(" ")); // join words into single string
        System.out.println(reverse);
        
        String result = Arrays.stream(name.split(" "))
                .map(str -> str.chars()
                        .filter(Character::isLetter)
                        .mapToObj(ch -> String.valueOf((char) ch))
                        .reduce(new StringBuilder(),
                                  (sb, ch) -> sb.insert(0, ch),       // accumulator: insert at start
                                  (sb1, sb2) -> sb1.append(sb2)       // combiner: append sb2 to sb1
                                )      
                        .toString())                               // convert StringBuilder to String
                .collect(Collectors.joining(" "));                 // join all words

        System.out.println(result);
        
        String collect = Arrays.stream(name.split(" ")).collect(Collectors.joining(", "));
        System.out.println(collect);
        
        String string = name.chars().mapToObj(ch->String.valueOf((char)ch)).reduce(new StringBuilder(),(sb,ch)->sb.insert(0,ch),(sb1,sb2)->sb1.append(sb2)).toString();
        System.out.println(string);
        
        string = name.chars().mapToObj(ch->String.valueOf((char)ch)).reduce("",(a,b)->b+a);
        System.out.println(string);
	
	}
}
