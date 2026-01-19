package com.zensar.repository;
import org.springframework.data.repository.CrudRepository;

import com.zensar.entity.Employees;


public interface EmployeeRepo extends CrudRepository<Employees, Integer> {


}
