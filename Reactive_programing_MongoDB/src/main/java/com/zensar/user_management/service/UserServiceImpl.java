package com.zensar.user_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.user_management.dto.UserDTO;
import com.zensar.user_management.entity.User;
import com.zensar.user_management.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Override
	public Mono<ResponseEntity<UserDTO>> insertUser(UserDTO userDTO) {
	    
		User user = this.getUser(userDTO);

	    return sequenceGenerator.generateSequence("user_sequence")
	        .flatMap(id -> {
	            user.setId(id);
	            return userRepository.save(user);
	        })
	        .flatMap(savedUser -> getUserDTO(Mono.just(savedUser)))
	        .flatMap(dto -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(dto)));
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteUser(Integer id) {
	    return userRepository.findById(id)
	        .flatMap(user -> userRepository.delete(user)
	        		.thenReturn(ResponseEntity.status(HttpStatus.OK).<Void>build()))
	        .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
	}
	
	@Override
	public Mono<ResponseEntity<UserDTO>> updateUser(Integer id, UserDTO userDTO) {
	    return userRepository.findById(id)
	        .flatMap(existingUser -> {
	            existingUser.setName(userDTO.getName());
	            existingUser.setAddress(userDTO.getAddress());
	            return userRepository.save(existingUser)
	                .flatMap(savedUser -> getUserDTO(Mono.just(savedUser)))
	                .flatMap(updatedDTO -> Mono.just(ResponseEntity.status(HttpStatus.OK).body(updatedDTO)));
	        })
	        .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
	}
	@Override
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsers() {
	    
		Flux<UserDTO> userFlux = getUserDTOFlux(userRepository.findAll());

	    return userFlux.hasElements()
	        .flatMap(hasUsers -> {
	            if (Boolean.TRUE.equals(hasUsers)) {
	                return Mono.just(ResponseEntity.status(HttpStatus.OK).body(userFlux));
	            } else {
	                return Mono.just(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	            }
	        });
	}

	@Override
	public Mono<ResponseEntity<UserDTO>> getUserById(Integer id) {
	    return userRepository.findById(id)
	        .flatMap(user -> getUserDTO(Mono.just(user))
	            .flatMap(dto -> Mono.just(ResponseEntity.status(HttpStatus.OK).body(dto))))
	        .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
	}

	public User getUser(UserDTO dto) {
		User target = new User();
		BeanUtils.copyProperties(dto, target);
		return target;
	}
	
	public Mono<UserDTO> getUserDTO(Mono<User> user) {
		return user.flatMap(userData->{
			UserDTO target = new UserDTO();
			BeanUtils.copyProperties(userData, target);
			return Mono.just(target);
		});
	}
	
	public Flux<UserDTO> getUserDTOFlux(Flux<User> users) {
		return users.flatMap(source->{
			UserDTO target = new UserDTO();
			BeanUtils.copyProperties(source, target);
			return Mono.just(target);
		});
	}
}