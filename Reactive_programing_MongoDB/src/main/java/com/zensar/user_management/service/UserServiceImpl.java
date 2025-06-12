package com.zensar.user_management.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Mono<UserDTO> insertUser(UserDTO userDTO) {
		User user = this.getUser(userDTO);
		return sequenceGenerator.generateSequence("user_sequence").flatMap(id -> {
			user.setId(id);
			return getUserDTO(userRepository.save(user));
		});
	}

	@Override
	public Mono<Void> deleteUser(Integer id) {
		return userRepository.deleteById(id);
	}

	@Override
	public Mono<UserDTO> updateUser(Integer id, UserDTO user) {
		return userRepository.findById(id).flatMap(existingUser -> {
			existingUser.setName(user.getName());
			existingUser.setAddress(user.getAddress());
			return getUserDTO(userRepository.save(existingUser));
		});
	}

	@Override
	public Flux<UserDTO> getAllUsers() {
		return getUserDTOFlux(userRepository.findAll());
	}

	@Override
	public Mono<UserDTO> getUserById(Integer id) {
		return getUserDTO(userRepository.findById(id));
	}

	public User getUser(UserDTO dto) {
		User target = new User();
		BeanUtils.copyProperties(dto, target);
		return target;
	}
	
	public Mono<UserDTO> getUserDTO(Mono<User> user) {
		return user.map(userData->{
			UserDTO target = new UserDTO();
			BeanUtils.copyProperties(userData, target);
			return target;
		});
	}
	
	public Flux<UserDTO> getUserDTOFlux(Flux<User> users) {
		return users.map(source->{
			UserDTO target = new UserDTO();
			BeanUtils.copyProperties(source, target);
			return target;
		});
	}
}