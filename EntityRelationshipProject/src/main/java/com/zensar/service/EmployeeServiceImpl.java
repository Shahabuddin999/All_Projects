package com.zensar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.entity.EmployeeEntity;
import com.zensar.json.Employee;
import com.zensar.repo.EmployeeRepo;
import com.zensar.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public Employee createEmployee(Employee employee) {
		EmployeeEntity employeeEntity = employeeRepo.save(EmployeeUtil.convertEmployeeIntoEmployeeEntity(employee));
		return EmployeeUtil.convertEmployeeEntityIntoEmployee(employeeEntity);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return EmployeeUtil.convertEmployeeEntityListIntoEmployeeList(employeeRepo.findAll());
	}

	@Override
	public List<Employee> getEmployeesByExperience(double experience) {
		List<EmployeeEntity> employeeEntityList = employeeRepo.findByEmployeeByExperience(experience);
		return EmployeeUtil.convertEmployeeEntityListIntoEmployeeList(employeeEntityList);
	}
	
	@Override
	public List<Employee> getEmployeesByProfileId(Long profileId) {
		List<EmployeeEntity> employeeEntityList = employeeRepo.getEmployeesByProfileId(profileId);
		return EmployeeUtil.convertEmployeeEntityListIntoEmployeeList(employeeEntityList);
	}

}
