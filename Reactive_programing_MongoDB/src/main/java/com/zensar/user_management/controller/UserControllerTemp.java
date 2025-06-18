package com.zensar.user_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.user_management.dto.UserDTO;

import com.zensar.user_management.service.UserServiceTemp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserControllerTemp {
	
	@Autowired
	private UserServiceTemp userService;

	@PostMapping("/save")
	public Mono<ResponseEntity<UserDTO>> insertUser(@RequestBody UserDTO user) {
		return userService.insertUser(user);
	}

	@GetMapping(value="/getall",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(value="/get/{id}")
	public Mono<ResponseEntity<UserDTO>> getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}

	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<UserDTO>> updateUser(ServerHttpRequest request, @PathVariable("id") Integer id, @RequestBody UserDTO user) {
		return userService.updateUser(request, id, user);
	}

	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") Integer id) {
		return userService.deleteUser(id);
	}
	
	@GetMapping(value="/getwebclient/{id}")
	public Mono<ResponseEntity<UserDTO>> getUserByIdWebClient(@PathVariable("id") Integer id) {
		return userService.getUserByIdWebClient(id);
	}
	
	@PostMapping("/savewebclient")
	public Mono<ResponseEntity<UserDTO>> insertUserWebclient(@RequestBody UserDTO user) {
		return userService.insertUserWebclient(user);
	}
	
	@GetMapping(value="/getallwebclient",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsersWebClient() {
		return userService.getAllUsersWebClient();
	}
}