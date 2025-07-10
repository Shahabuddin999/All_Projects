package com.zensar.temp;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HelloWorld {
	String []array = {"a1","a2","a3","b1","b2","b3"};
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    public static void main(String[] args) {
    	Arrays.stream(new HelloWorld().array).filter(val->val!=null && val.startsWith("a")).forEach(value->logger.info(value));
    }
}