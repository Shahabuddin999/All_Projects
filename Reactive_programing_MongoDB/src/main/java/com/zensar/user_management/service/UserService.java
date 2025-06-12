package com.zensar.user_management.service;
import com.zensar.user_management.dto.UserDTO;
import com.zensar.user_management.entity.User;
 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface UserService {
	public Mono<UserDTO>insertUser(UserDTO user);
	public Mono<Void>deleteUser(Integer  id);
	public Mono<UserDTO> updateUser(Integer id, UserDTO user);
	public Flux<UserDTO> getAllUsers();
	public Mono<UserDTO> getUserById(Integer id); 
}