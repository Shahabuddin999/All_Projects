package com.zensar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/all")
	public String viewAll() {
		return "Hello All";
	}

	@GetMapping("/employee")
	public String viewEmployee() {
		return "Hello Employee";
	}

	@GetMapping("/admin")
	public String viewAdmin() {
		return "Hello Admin";
	}
}