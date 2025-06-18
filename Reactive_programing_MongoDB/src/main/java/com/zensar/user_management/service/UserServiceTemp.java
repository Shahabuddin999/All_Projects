package com.zensar.user_management.service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import com.zensar.user_management.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserServiceTemp {
	public Mono<ResponseEntity<UserDTO>> insertUser(UserDTO user);
	public Mono<ResponseEntity<Void>> deleteUser(Integer  id);
	public Mono<ResponseEntity<UserDTO>> updateUser(ServerHttpRequest request, Integer id, UserDTO user);
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsers();
	public Mono<ResponseEntity<UserDTO>> getUserById(Integer id);
	public Mono<ResponseEntity<UserDTO>> getUserByIdWebClient(Integer id);
	public Mono<ResponseEntity<UserDTO>> insertUserWebclient(UserDTO user);
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsersWebClient(); 
}