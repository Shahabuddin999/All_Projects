package com.k8s.repository;
import org.springframework.data.repository.CrudRepository;

import com.k8s.entity.CompanyData;


public interface CompanyRepo extends CrudRepository<CompanyData, Integer> {
}
