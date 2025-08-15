package com.zensar.temp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
public class ParallelStream {

	public static void main(String[] args) {

		        // Simulate a large list of users
		        List<User> users = new ArrayList<>();
		        for (int i = 1; i <= 100000; i++) {
		            users.add(new User(i, "User" + i, 20 + (i % 30), i % 2 == 0));
		        }

		        long start = System.currentTimeMillis();
		        // Use parallel stream
		        List<UserDTO> result = users.parallelStream()
		            .filter(user -> user.isActive() && user.getAge() > 25)
		            .map(user -> new UserDTO(user.getName(), user.getAge()))
		            .collect(Collectors.toList());

		        //result.forEach(val->System.out.println("result : "+val));
		        long end = System.currentTimeMillis();
		        System.out.println("1st Time taken: " + (end - start) + " ms");
		        
		        
		        long start1 = System.currentTimeMillis();
		        // Use stream
		        List<UserDTO> result1 = users.stream()
		            .filter(user -> user.isActive() && user.getAge() > 25)
		            .map(user -> new UserDTO(user.getName(), user.getAge()))
		            .collect(Collectors.toList());

		        //result1.forEach(val->System.out.println("result1 : "+val));
		        long end1 = System.currentTimeMillis();
		        System.out.println("2nd Time taken: " + (end1 - start1) + " ms");
		        
		        
		        
		        //result.parallelStream().forEach(car -> System.out.println(Thread.currentThread().getName() + " - " + car));
		        //result1.stream().forEach(car -> System.out.println(Thread.currentThread().getName() + " - " + car));
		        
		        
		        String name = "Shahabuddin";
		        Map<String, Long> collect = Arrays.stream(name.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		        System.out.println(collect);
		        Map<Character, Long> collect2 = name.chars().mapToObj(ascii->(char)ascii).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		        System.out.println(collect2);
		        
		        String str = "qwerty";
		        System.out.println(str.length());
		        System.out.println(str.substring(2, 4)); // 2 included 4 excluded, index starts from 0
		        System.out.println(str.substring(2, str.length()));
	}
}
