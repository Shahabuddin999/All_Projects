package com.example.lambda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lambda.entity.Employee;
import com.example.lambda.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class HelloController {
	
	@Autowired
    private EmployeeService employeeService;
	
    @GetMapping("/hello")
    public String hello() {
        return "Hello from  Spring Boot on AWS Lambda, You have learned LAMBDA !!!";
    }
    
    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return employeeService.getEmployee();
    }

    // POST create a new employee
    @PostMapping("/save")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}
