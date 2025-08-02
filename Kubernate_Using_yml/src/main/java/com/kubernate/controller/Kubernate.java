package com.kubernate.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Kubernate {
    
   
    @GetMapping(value = "/kubernate")
    public String welcomePage() {
    	
    	return "Hi, welcome on Kubernetes using yml file, Perfectly running";
    }
	
	@GetMapping(value = "/employee")
    public String index() {
    	
    	return "Hi employee, welcome on Kubernetes using yml file, Greate job, Perfectly running";
    }
}
