package com.webflux.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.demo.entity.Order;

import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {
    Flux<Order> findByUserId(Integer userId);
}