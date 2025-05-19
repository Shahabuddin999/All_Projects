package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EuderekaServerApplication {

	public static void main(String[] args) {
		System.out.println("Ureka server started.......");
		SpringApplication.run(EuderekaServerApplication.class, args);
	}

}
