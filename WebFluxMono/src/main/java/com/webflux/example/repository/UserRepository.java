package com.webflux.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.example.entity.User;


public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
