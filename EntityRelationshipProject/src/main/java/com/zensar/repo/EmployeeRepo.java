package com.zensar.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entity.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long>{
	
	@Query("SELECT emp FROM EmployeeEntity as emp WHERE emp.profileObject.experience >= :experience")
	List<EmployeeEntity> findByEmployeeByExperience(double experience);
	
	@Query("SELECT emp FROM EmployeeEntity as emp WHERE emp.profileObject.id = :id")
	List<EmployeeEntity> getEmployeesByProfileId(Long id);
}
