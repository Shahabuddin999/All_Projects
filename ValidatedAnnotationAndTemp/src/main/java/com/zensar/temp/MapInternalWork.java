package com.zensar.temp;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class Empployee{
	int age;
	String company;
	Empployee(int age, String company){
		this.age = age;
		this.company = company;
	}
	@Override
	public String toString() {
		return "Empployee [age=" + age + ", company=" + company + "]";
	}
	
}
public class MapInternalWork {

	public static void main(String[] args) {
		Empployee e1 = new Empployee(10,"Zensar");
		Empployee e2 = new Empployee(11,"Zensar");
		Empployee e3 = new Empployee(12,"Zensar");
		Map<Empployee,String> map = new LinkedHashMap<>();
		String put = map.put(e1, "aaa");
		map.put(e2, "bbb");
		map.put(e3, "ccc");
		map.put(e3, "ddd");
		map.put(e3, "ddd");
		System.out.println(put);
		System.out.println(map);
		
		Set<String> set = new HashSet<>();
		boolean a1 = set.add("Shahab");
		boolean a2 = set.add("Ansari");
		boolean a3 = set.add("Shahab");
		
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		
	}

}
