package com.example.lambda.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.lambda.entity.Employee;

public interface EmployeeService {

	ResponseEntity<List<Employee>> getEmployee();

	ResponseEntity<Employee> saveEmployee(Employee employee);
}
