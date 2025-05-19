package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Product;
import com.zensar.service.AWSService;
@RestController
@CrossOrigin(origins="*")
public class TestController {

	public TestController() {
	}
	@Autowired
	AWSService  aWSService;
	
	@GetMapping("/microproduct")
	public @ResponseBody List<Product> getAllProducts() {
		return aWSService.getAllProducts();
	}
}
