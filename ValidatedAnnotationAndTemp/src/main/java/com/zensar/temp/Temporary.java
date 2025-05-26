package com.zensar.temp;

import java.util.Arrays;

public class Temporary {
    public static void main(String[] args) {
        String input = "My name is Shahabuddin";

        Arrays.stream(input.split(" "))  // Split the input into words
              .forEach(word -> 
                  System.out.println(word + ":" + word.length())  // Print word and its length
              );
    }
}
