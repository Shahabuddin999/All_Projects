package com.zensar.service;

import java.util.List;

import com.zensar.json.Employee;


public interface EmployeeService {
	public Employee createEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public List<Employee> getEmployeesByExperience(double experience);
	public List<Employee> getEmployeesByProfileId(Long experience);
}
