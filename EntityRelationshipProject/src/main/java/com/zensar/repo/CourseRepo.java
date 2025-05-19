package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Long>{

}
