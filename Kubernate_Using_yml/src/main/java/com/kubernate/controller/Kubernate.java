package com.kubernate.controller;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Kubernate {
    
   
    @GetMapping(value = "/kubernate")
    public String welcomePage() throws UnknownHostException {
    	
    	// I have given replica:2 means 2 POD will be created and here both POD will consume the Request Parallel and here I m checking which POD is consuming the user's request using just below line
    	return "In String welcomePage() method, Handling request from pod: " + InetAddress.getLocalHost().getHostName();
    }
	
	@GetMapping(value = "/employee")
    public String index() throws UnknownHostException {
		
		// I have given replica:2 means 2 POD will be created and here both POD will consume the Request Parallel and here I m checking which POD is consuming the user's request using just below line
		return "In String index() method, Handling request from pod: " + InetAddress.getLocalHost().getHostName();
    }
}
