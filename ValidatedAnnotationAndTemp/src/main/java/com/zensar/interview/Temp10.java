package com.zensar.interview;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Temp10 {

	public static void main(String[] args) {

		String string = "1 10 100 22 33 101 506 44";
		Map<Integer,String> map = new LinkedHashMap<>();
		Arrays.stream(string.split(" ")).sorted((st1,st2)->st1.length()-st2.length()).forEach(str->{
			
			if(str.length()==1) {
				map.put(1, map.get(1)+" "+str);
			}else if(str.length()==2) {
				map.put(2, map.get(2)+" "+str);
			}else if(str.length()==3) {
				map.put(3, map.get(3)+" "+str);
			}
		});
		map.entrySet().stream().forEach(mp->{
			System.out.println(mp.getValue().replace("null ", ""));
		});
	}

}
