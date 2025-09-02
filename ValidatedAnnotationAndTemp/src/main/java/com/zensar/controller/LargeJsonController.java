package com.zensar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.configure.MyEmloyee;

import java.util.*;

@RestController
public class LargeJsonController {

	@Autowired
	MyEmloyee emp;
	
    @GetMapping("/large-json")
    public List<Map<String, String>> getLargeJson() {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(i));
            map.put("name", "Item " + i);
            map.put("description", "This is a long description for item number " + i);
            list.add(map);
        }
        return list;
    }
    
    @GetMapping("/setflag")
    public void setFlag(){
    	emp.flag++;
    }
}
