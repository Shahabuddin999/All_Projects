package com.zensar.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//@Order(1)
public class EmailRunner implements ApplicationRunner, Ordered{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("inside ApplicationRunner");
		for(int i=0; i<args.getSourceArgs().length; i++) {
			System.out.println(args.getSourceArgs()[i]);
		}
		
	}

	@Override
	public int getOrder() {
		return 2;
	}

}
