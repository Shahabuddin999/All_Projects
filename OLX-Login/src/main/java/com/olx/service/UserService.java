package com.olx.service;

import org.springframework.http.ResponseEntity;

import com.olx.dto.UserDto;

public interface UserService {
	ResponseEntity<Boolean> userLogout(String authTocken);
	ResponseEntity<UserDto> getUser(String authTocken, String userName);
	ResponseEntity<UserDto> createUser(UserDto user);
	ResponseEntity<Boolean> validateTocken(String authTocken);
	ResponseEntity<UserDto> changePassword(UserDto user);
}
