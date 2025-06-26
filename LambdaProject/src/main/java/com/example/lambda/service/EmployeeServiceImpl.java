package com.example.lambda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.lambda.entity.Employee;
import com.example.lambda.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public ResponseEntity<List<Employee>> getEmployee() {
		List<Employee> categoryEntity = employeeRepository.findAll();
		if (!categoryEntity.isEmpty())
			return new ResponseEntity(categoryEntity, HttpStatus.FOUND);
		else
			return new ResponseEntity(new ArrayList<Employee>(), HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<Employee> saveEmployee(Employee employee) {
		return new ResponseEntity(employeeRepository.save(employee), HttpStatus.CREATED);
	}

}
