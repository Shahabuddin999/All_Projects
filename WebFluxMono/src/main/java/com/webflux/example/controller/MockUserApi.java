package com.webflux.example.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class MockUserApi {
	Logger logger = LoggerFactory.getLogger(MockUserApi.class);
    @GetMapping("/{id}")
    public Mono<String> getUser(@PathVariable String id) throws InterruptedException {
       
    	return Mono.fromCallable(()->{
    		Thread.sleep(1000);
    		logger.info("User Found in DB: "+id);
    		return "User Found in DB: "+id;
    	});
    }
}
