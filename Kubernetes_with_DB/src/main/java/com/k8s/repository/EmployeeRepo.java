package com.k8s.repository;
import org.springframework.data.repository.CrudRepository;

import com.k8s.entity.EmployeesData;

public interface EmployeeRepo extends CrudRepository<EmployeesData, Integer> {


}
