package com.zensar.repository;
import org.springframework.data.repository.CrudRepository;

import com.zensar.entity.Company;


public interface CompanyRepo extends CrudRepository<Company, Integer> {
}
