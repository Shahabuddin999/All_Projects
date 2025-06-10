package com.webflux.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.webflux.demo.dto.OrderDTO;
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Getting from OrderDTO : "+new OrderDTO(1L, "Mobile").item());
		System.out.println("Great job Application is UP & Running on Docker now Properly [][][][][][][][][]<<<<<<<<<<< ");
	}

}
