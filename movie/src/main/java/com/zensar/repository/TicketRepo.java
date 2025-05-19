package com.zensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entity.TicketEntity;

public interface TicketRepo extends JpaRepository<TicketEntity, Integer> {}
