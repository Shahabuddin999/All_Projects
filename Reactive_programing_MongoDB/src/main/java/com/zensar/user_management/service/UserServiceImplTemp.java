package com.zensar.user_management.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zensar.user_management.dto.UserDTO;
import com.zensar.user_management.entity.User;
import com.zensar.user_management.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImplTemp implements UserServiceTemp{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	private final WebClient webClient;

    public UserServiceImplTemp(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080").build();
    }
	
	@Override
	public Mono<ResponseEntity<UserDTO>> insertUser(UserDTO user) {
		
		return sequenceGenerator.generateSequence("user_sequence").flatMap(id->{
			User userEntityObject = this.getUser(user);
			userEntityObject.setId(id);
			return userRepository.save(userEntityObject).map(saved->{
				return ResponseEntity.status(HttpStatus.CREATED).body(getUserDTO(saved));
			});
		});	 
	}
	
	@Override
	public Mono<ResponseEntity<Void>> deleteUser(Integer id) {
		
		return userRepository.findById(id).flatMap(user->userRepository.delete(user)
					.thenReturn(ResponseEntity.status(HttpStatus.OK).<Void>build())
				).switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
	}

	@Override
	public Mono<ResponseEntity<UserDTO>> updateUser(ServerHttpRequest request,Integer id, UserDTO userDTO) {
	
		return userRepository.findById(id).flatMap(existingUser -> {
			BeanUtils.copyProperties(userDTO, existingUser, "id");
			return userRepository.save(existingUser)
					.map(val -> new ResponseEntity<UserDTO>(getUserDTO(val), request.getHeaders(), HttpStatus.CREATED));
		})
		.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
	}

	@Override
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsers() {
	
		Flux<UserDTO> userFlux = userRepository.findAll().map(this::getUserDTO);
		return userFlux.hasElements().map(flag->{
			if(Boolean.TRUE.equals(flag))
				return ResponseEntity.status(HttpStatus.FOUND).body(userFlux);
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		});
	}
	
	@Override
	public Mono<ResponseEntity<UserDTO>> getUserById(Integer id) {

		return userRepository.findById(id)
				.map(user->ResponseEntity.status(HttpStatus.FOUND).body(getUserDTO(user)))
				.switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
	}

	@Override
	public Mono<ResponseEntity<UserDTO>> getUserByIdWebClient(Integer id) {
		return webClient.get()
				.uri("/get/{id}",id)
				.retrieve()
				.onStatus(status -> status.is4xxClientError(), response -> {
		           // if (response.statusCode().equals(HttpStatus.NOT_FOUND)) {
		            //	System.out.println(response.statusCode());
		                return Mono.empty(); // will be handled in switchIfEmpty
		            //}
		            //return response.createException().flatMap(Mono::error);
		        })
				.bodyToMono(UserDTO.class)
	            .map(user -> new ResponseEntity<>(user,null,HttpStatus.FOUND))
	            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
	}

	@Override
	public Mono<ResponseEntity<UserDTO>> insertUserWebclient(UserDTO user) {
		return webClient.post()
				.uri("/save")
				.bodyValue(user)
				.retrieve()
				.bodyToMono(UserDTO.class)
				.map(userDTO-> new ResponseEntity<>(userDTO,null,HttpStatus.CREATED));
				
	}

	@Override
	public Mono<ResponseEntity<Flux<UserDTO>>> getAllUsersWebClient() {
		
		Flux<UserDTO> flux = webClient.get()
				.uri("/getall")
				.retrieve()
				.onStatus(status->status.is4xxClientError(), response->{
					return Mono.empty();
				}).bodyToFlux(UserDTO.class);
				
		return flux.hasElements().flatMap(flag->{
			if(Boolean.TRUE.equals(flag)) {
				return Mono.just(ResponseEntity.status(HttpStatus.FOUND).body(flux));
			} else
				return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		});
	}
	
	public User getUser(UserDTO dto) {
		User target = new User();
		BeanUtils.copyProperties(dto, target);
		return target;
	}
	
	public UserDTO getUserDTO(User user) {
			UserDTO target = new UserDTO();
			BeanUtils.copyProperties(user, target);
			return target;
	}	
}
