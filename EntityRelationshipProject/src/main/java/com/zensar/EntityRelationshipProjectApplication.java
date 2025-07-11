package com.zensar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EntityRelationshipProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityRelationshipProjectApplication.class, args);
		System.out.println("Application is up and running.......");
	}
	
	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
