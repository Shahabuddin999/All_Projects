package com.zensar.temp;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LoggerExample implements Runnable{
	String []array = {"a1","a2","a3","b1","b2","b3"};
	private static final Logger logger = LoggerFactory.getLogger(LoggerExample.class);
    public static void main(String[] args) {
    	new Thread(new LoggerExample()).start();
    }
	@Override
	public void run() {
		Arrays.stream(new LoggerExample().array).filter(val->val!=null && val.startsWith("a")).forEach(value->logger.info(value));
	}
}