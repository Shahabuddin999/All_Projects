package com.webflux.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.webflux.demo.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
