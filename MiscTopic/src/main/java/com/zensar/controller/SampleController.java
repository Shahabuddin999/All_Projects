package com.zensar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.config.AppConfig;
@RestController
public class SampleController {
	@Autowired
	AppConfig config;
	
	@GetMapping(value="/api/appName")
	public String getName() {
		return "App name : "+config.getAppName();
	}
}
