package com.zensar.user_management.repository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
 
import com.zensar.user_management.entity.User;
 
public interface UserRepository extends ReactiveMongoRepository<User, Integer> {
 
}