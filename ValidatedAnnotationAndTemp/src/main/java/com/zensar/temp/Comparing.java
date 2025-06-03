package com.zensar.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Emp{
	int age;
	String name;
	Emp(int age, String name){
		this.age=age;
		this.name=name;
	}
	@Override
	public String toString() {
		return "Emp [age=" + age + ", name=" + name + "]";
	}
	
}
public class Comparing {
	public static void main(String[] args) {
		List<Emp> list = new ArrayList<>();
		list.add(new Emp(10,"shahab"));
		list.add(new Emp(5,"nizam"));
		list.add(new Emp(8,"kalam"));
		
		Comparator<Emp> cmp= (obj1, obj2)->obj1.age-obj2.age;
		Collections.sort(list,cmp);
		System.out.println(list);
		
		List<Emp> filter = list.stream().filter(val->val.age>8).collect(Collectors.toList());
		System.out.println(filter);
		List<Integer> listData = Arrays.asList(10,21,51,8,4);
		System.out.println(listData.stream().min((a,b)->a>b?1:-1).get());
		Optional<Integer> v = listData.stream().filter(num->num%2==0).reduce((a,b)->a+b);
		v.ifPresent(val->System.out.println(val));
	}
}
