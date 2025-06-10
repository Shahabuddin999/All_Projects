package com.example.transactional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transactional.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
