package com.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class LoadBalancerProjectApplication {

	public static void main(String[] args) {
		log.info("application started...");
		SpringApplication.run(LoadBalancerProjectApplication.class, args);
	}

}
