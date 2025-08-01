package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.json.Employee;
import com.zensar.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/myapp")
public class EmployeeProfileController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value="/test", produces=MediaType.APPLICATION_JSON_VALUE)
	public String test() {
		return "You have learned EC2 Congrats !!!";
	}
	
	@GetMapping(value="/employee", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@PostMapping(value="/employee", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee createNewEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping(value="/employee/profile/experience/{experience}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployeesByExperience(@PathVariable("experience") double experience) {
		return employeeService.getEmployeesByExperience(experience);
	}
	
	@GetMapping(value="/employee/profile/profileId/{profileId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployeesByProfileId(@PathVariable("profileId") Long profileId) {
		return employeeService.getEmployeesByProfileId(profileId);
	}
	
}
