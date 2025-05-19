package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zensar.Entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Integer>{
	List<StockEntity> findByName(String name);
	List<StockEntity> findByMarketName(String name);
	
	List<StockEntity> findByNameContains(String name);
	List<StockEntity> findByNameContaining(String name);
	List<StockEntity> findByNameIsContaining(String name);
	
	@Query("SELECT se FROM StockEntity se where se.name LIKE %:name%")
	List<StockEntity> findByNameByJpql(@Param("name") String name);

	@Query(value="select * from stock_entity u where u.name like %:name%", nativeQuery=true)
	List<StockEntity> findByNameBySql(@Param("name") String name);
	
	List<StockEntity> findByOrderByNameDesc();

}
