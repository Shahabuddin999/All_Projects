package com.example.lambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lambda.entity.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
}