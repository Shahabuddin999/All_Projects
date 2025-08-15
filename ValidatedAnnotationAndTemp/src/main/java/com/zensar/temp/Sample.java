package com.zensar.temp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sample {

	public static void main(String[] args) {
		List<Integer> list = List.of(100,200,400,300,50);
		list.stream().sorted().skip(2).limit(1).forEach(System.out::println);
		
		IntStream.rangeClosed(1, 20).forEach(num->{
			boolean noneMatch = IntStream.rangeClosed(2, (int)Math.sqrt(num)).noneMatch(val->num%val==0);
			if(noneMatch)
				System.out.println("Prime: "+num);
		});
		
		IntStream.rangeClosed(2, 300).forEach(num->{
			Integer reduce = String.valueOf(num).chars().mapToObj(ch->Character.getNumericValue(ch))
			.map(val->(int)Math.pow(val, 3)).reduce(0,(a,b)->a+b);
			
			if(reduce == num)
				System.out.println(reduce);
		});
		
		IntStream.rangeClosed(2, 300).forEach(num->{
			Integer reduce = Arrays.stream(String.valueOf(num).split("")).map(val->Integer.valueOf(val))
			.map(val->(int)Math.pow(val, 3)).reduce(0,(a,b)->a+b);
			if(reduce == num)
				System.out.println(reduce);
		});
		
		List<Integer> collect = IntStream.rangeClosed(2, 300).filter(num->{
			Integer reduce = Arrays.stream(String.valueOf(num).split("")).map(val->Integer.valueOf(val))
					.map(val-> val*val*val).reduce(0,(a,b)->a+b);	
					return reduce.equals(num);
		}).boxed().collect(Collectors.toList());
		System.out.println(collect);
		
		List<String> strList = List.of("AABCS","WERT","QWRTO");
		strList.stream().forEach(str->{
			long count = Arrays.stream(str.split("")).filter(ch->!"AEIOU".contains(ch)).count();
			System.out.println(str+":"+count);
		});
		
	}
}
