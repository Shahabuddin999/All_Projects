package com.zensar.interview;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Temp1 {
	
	 private static final Logger logger = LogManager.getLogger(Temp1.class);

	public static void main(String[] args) {
		List<String> list = Arrays.asList("today today is is monday".split(" "));
		Map<String,Integer> map = list.stream().collect(Collectors.toMap(Function.identity(),initialValue->1,Math::addExact));
		logger.debug(map);
		
		
		String []str="today today is is is monday".split(" ");
		Map<String,Integer> map1 = new LinkedHashMap<String,Integer>();
		for(int i=0; i<str.length; i++) {
			if(map1.containsKey(str[i])) {
				//map1.put(str[i],map1.get(str[i])+1);
				map1.replace(str[i], map1.get(str[i])+1);
			}else {
				map1.put(str[i], 1);
			}
		}
		logger.info(map1);
	}

}
