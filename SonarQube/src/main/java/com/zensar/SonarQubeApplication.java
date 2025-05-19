package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashSet;
@SpringBootApplication
public class SonarQubeApplication {
	private int a=10;
	public static void main(String[] args) {
		SpringApplication.run(SonarQubeApplication.class, args);
	}

}
