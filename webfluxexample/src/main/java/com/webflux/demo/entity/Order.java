package com.webflux.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private Integer id;
    private String product;
    private Integer userId;  // Foreign key
}
