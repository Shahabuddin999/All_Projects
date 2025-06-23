package com.webflux.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.example.entity.Order;

import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {
    Flux<Order> findByUserId(Integer userId);
}