package com.kubernate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Kubernate {
    
   
    @GetMapping(value = "/kubernate")
    public String welcomePage() {
    	
    	return "Hi everyone you'r welcome on Kubernetes Shahabuddin !!!!!!";
    }
}
