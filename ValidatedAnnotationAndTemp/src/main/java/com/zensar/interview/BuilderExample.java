package com.zensar.interview;
import java.util.stream.Stream;

public class BuilderExample {
    public static void main(String[] args) {
        Stream<String> stream = Stream.<String>builder()
                .add("A")
                .add("B")
                .add("C")
                .build();

        stream.forEach(System.out::println);
        
        Stream.Builder<Integer> builder = Stream.builder();

        builder.accept(10);
        builder.accept(20);
        builder.accept(30);

        Stream<Integer> numberStream = builder.build();
        numberStream.forEach(System.out::println);
       
    }
}
