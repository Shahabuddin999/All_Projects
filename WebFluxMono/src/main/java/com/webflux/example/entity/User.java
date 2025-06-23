package com.webflux.example.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Table("users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
    @Id
    private Integer id;
    private String name;
    private String email;
}
