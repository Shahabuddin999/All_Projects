package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entity.QuestionEntity;

public interface QuestionRepo extends JpaRepository<QuestionEntity, Long>{

}
