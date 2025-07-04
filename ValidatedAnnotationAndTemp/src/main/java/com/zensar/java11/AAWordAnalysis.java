package com.zensar.java11;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AAWordAnalysis {

    public static void main(String[] args) {
        List<String> words = Arrays.asList(
            "AANAYA", "ATHAH", "AAVCCANA", "ATACK", "AJAAYA", "AKIRA", "AARAV", "AJAYYY"
        );

        // Vowel set for checking
        Set<Character> vowels = Set.of('A', 'E', 'I', 'O', 'U');

        // Filter words that start with "AA" (case-insensitive)
        List<String> aaWords = words.stream()
            .filter(word -> word.toLowerCase().startsWith("aa"))
            .collect(Collectors.toList());

         //Print each word and its consonant count
//        for (String word : aaWords) {
//            long consonantCount = word.chars()
//                .filter(Character::isLetter)
//                .mapToObj(ch -> Character.toUpperCase((char) ch))
//                .filter(ch -> !vowels.contains(ch))
//                .count();
//
//            System.out.println("Word: " + word + ", Consonants: " + consonantCount);
//        }
        System.out.println("---------------------------------------------------------");
        aaWords.forEach(word -> {
            long consonantCount = word
                .toUpperCase()
                .chars()   // "ABC".chars();  // returns IntStream of 65, 66, 67
                //.filter(Character::isLetter)
                .filter(ch -> {
                	//System.out.println("ch : "+ch);
                	return Character.isLetter(ch);}) // This line and just above line is same
                .filter(ch -> !"AEIOU".contains(String.valueOf((char) ch)))
                .count();

            System.out.println("Word: " + word + ", Consonants: " + consonantCount);
        });

        // Print total count
        System.out.println("Total words starting with 'AA': " + aaWords.size());
        
        "ABC".chars().forEach(System.out::println);
        
        System.out.println("---------------------------------------------------------");

        words.stream().filter(val->val.startsWith("AA")).collect(Collectors.toList())
        .stream().forEach(val->{
        	long count = val.chars().filter(ch->Character.isLetter(ch))
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
        
    }
}
