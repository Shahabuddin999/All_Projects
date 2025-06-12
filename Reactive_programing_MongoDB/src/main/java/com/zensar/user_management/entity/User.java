package com.zensar.user_management.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "UserCollection")
public class User {
	@Id
	private Integer id;
	private String name;
	private String address;
}