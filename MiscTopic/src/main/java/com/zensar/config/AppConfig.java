package com.zensar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Bean
	public String getAppName() {
		return appName;
	}
}
