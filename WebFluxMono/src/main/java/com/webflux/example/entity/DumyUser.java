package com.webflux.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("shahabuddin")
@AllArgsConstructor
@NoArgsConstructor
public class DumyUser {
	@Id
    private Integer id;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String mobile;
}
