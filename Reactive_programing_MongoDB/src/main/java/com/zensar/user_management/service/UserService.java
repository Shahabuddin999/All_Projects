package com.zensar.user_management.service;
import org.springframework.http.ResponseEntity;

import com.zensar.user_management.dto.UserDTO;
import com.zensar.user_management.entity.User;
 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface UserService {
	public Mono<ResponseEntity<UserDTO>> insertUser(UserDTO user);
	public Mono<ResponseEntity<Void>> deleteUser(Integer  id);
	public Mono<ResponseEntity<UserDTO>> updateUser(Integer id, UserDTO user);
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsers();
	public Mono<ResponseEntity<UserDTO>> getUserById(Integer id); 
}