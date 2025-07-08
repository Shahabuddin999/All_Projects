package com.olx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient 
public class OlxAdvertisesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxAdvertisesApplication.class, args);
		System.out.println("Advertise server started.....");
	}
}

// http://localhost:8083/swagger-ui/index.html