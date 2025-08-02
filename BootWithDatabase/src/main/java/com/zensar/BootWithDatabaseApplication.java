package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootWithDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootWithDatabaseApplication.class, args);
		System.out.println("BootWithDatabaseApplication is Up and runing on Docker !!!!!!!");
	}
}
