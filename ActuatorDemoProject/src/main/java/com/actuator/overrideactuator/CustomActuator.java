package com.actuator.overrideactuator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="bug-fix")
public class CustomActuator {
	
	private Map<String, List<String>> map = new HashMap<String, List<String>>();
	
	@PostConstruct
	public void init() {
		map.put("v1", Arrays.asList("AA","BB","CC"));
		map.put("v2", Arrays.asList("DD","EE","FF"));
	}
	@ReadOperation
	public Map<String, List<String>> getAllDataa(){
		return map;
	}
	
	@ReadOperation
	public List<String> getSpecific(@Selector String selector){
		return map.get(selector);
	}
	
	/*
	 Method : POST
	 http://localhost:8088/olxapp/bug-fix/v4
	 {
    	"newBug": "app5,app6"
	 }
	 Note : key of the json 'newBug' should be equals of @WriteOperation's method second parameter String 'newBug'
	 */
	@WriteOperation
	public boolean addNewBug(@Selector String version, String newBug) {
		System.out.println("write");
		map.put(version, Arrays.asList(newBug.split(",")));
		return true;
	}
	
	@DeleteOperation
	public boolean deleteBug(@Selector String version) {
		System.out.println("delete");
		if(map.containsKey(version)) {
			map.remove(version);
			return true;
		}else
			return false;
	}
}
