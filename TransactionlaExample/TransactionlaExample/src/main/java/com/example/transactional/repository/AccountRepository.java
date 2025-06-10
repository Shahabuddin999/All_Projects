package com.example.transactional.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transactional.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {}
