package com.loadbalancer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
    	String msg ="";
    	try {
    		
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("Host Name: " + address.getHostName());
            System.out.println("IP Address: " + address.getHostAddress());
            msg = "Host Name: " + address.getHostName()+"<br/> IP Address: "+address.getHostAddress();
            log.info(msg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
