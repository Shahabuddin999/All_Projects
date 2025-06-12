package com.zensar.user_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.user_management.dto.UserDTO;
import com.zensar.user_management.entity.User;
import com.zensar.user_management.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public Mono<UserDTO> insertUser(@RequestBody UserDTO user) {
		return userService.insertUser(user);
	}

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public Mono<UserDTO> getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	public Mono<UserDTO> updateUser(@PathVariable("id") Integer id, @RequestBody UserDTO user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable("id") Integer id) {
		return userService.deleteUser(id);
	}
}