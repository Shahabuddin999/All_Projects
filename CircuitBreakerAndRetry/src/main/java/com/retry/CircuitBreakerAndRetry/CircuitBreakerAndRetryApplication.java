package com.retry.CircuitBreakerAndRetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true) 
public class CircuitBreakerAndRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerAndRetryApplication.class, args);
	}

}
