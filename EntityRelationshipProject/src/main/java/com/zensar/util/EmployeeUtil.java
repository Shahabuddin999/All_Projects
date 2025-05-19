package com.zensar.util;

import java.util.ArrayList;
import java.util.List;

import com.zensar.entity.EmployeeEntity;
import com.zensar.entity.ProfileEntity;
import com.zensar.json.Employee;
import com.zensar.json.Profile;

public class EmployeeUtil {

	public static Employee convertEmployeeEntityIntoEmployee(EmployeeEntity employeeEntity) {
		ProfileEntity profileEntity = employeeEntity.getProfile();
		Profile profile = new Profile(profileEntity.getId(), profileEntity.getCompany(), profileEntity.getExperience());
		return new Employee(employeeEntity.getEmployeeId(), employeeEntity.getName(), employeeEntity.getSal(), profile);
	}

	public static EmployeeEntity convertEmployeeIntoEmployeeEntity(Employee employee) {
		Profile profile = employee.getProfile();
		ProfileEntity profileEntity = new ProfileEntity(profile.getCompany(), profile.getExperience());
		return new EmployeeEntity(employee.getName(), employee.getSal(), profileEntity);
	}

	public static List<Employee> convertEmployeeEntityListIntoEmployeeList(List<EmployeeEntity> employeeEntityList) {
		List<Employee> employees = new ArrayList<Employee>();
		for(EmployeeEntity employeeEntity: employeeEntityList) {
			employees.add(convertEmployeeEntityIntoEmployee(employeeEntity));
		}
		return employees;
	}
}
