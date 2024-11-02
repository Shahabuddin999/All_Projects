package com.olx.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.document.BlackListedToken;
import com.olx.dto.UserDto;
import com.olx.entity.UserEntity;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidUserNameOrPasswordException;
import com.olx.exception.UserNameAlreadyExistsException;
import com.olx.repository.UserLogoutMongoRepository;
import com.olx.repository.UserRepo;
import com.olx.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepository;
	
	@Autowired
	UserLogoutMongoRepository userLogoutMongoRepository;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	PasswordEncoder passwordEncoder; 
//	@Override
//	public ResponseEntity<String> authenticate(UserDto userDto) {
//		List<UserEntity> list = userRepository.findUserByUserName(userDto.getUsername());
//		if (list.isEmpty()) {
//			UserEntity userEntity = this.modelMapper.map(userDto, UserEntity.class);
//			userEntity = userRepository.save(userEntity);
//			userDto = this.modelMapper.map(userEntity, UserDto.class);
//			return new ResponseEntity<String>("LoggedIn",HttpStatus.CREATED);
//		} else {
//			throw new InvalidUserNameOrPasswordException(userDto.getUsername());
//		}
//	}

	@Override
	public ResponseEntity<Boolean> userLogout(String authToken) {
		
		String token = authToken.split(" ")[1];
		try {
		Date extractExpiration = jwtUtil.extractExpiration(token);
		LocalDateTime expiryDateTime = extractExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		BlackListedToken balBlackListedToken = new BlackListedToken(1, token, LocalDateTime.now(), expiryDateTime);
		userLogoutMongoRepository.save(balBlackListedToken);
		return new ResponseEntity<Boolean>(true,HttpStatus.FOUND);
		} catch (Exception e) {
			throw new InvalidAuthTokenException("You have intered invalid Auth Thocken");
		}
	}

	@Override
	public ResponseEntity<UserDto> getUser(String authTocken, String userName) {
		List<UserEntity> list = userRepository.findUserByUserName(userName);
		if (!list.isEmpty()) {
			UserDto userDto = this.modelMapper.map(list.get(0), UserDto.class);
			userDto.setPassword(this.encodePassword(userDto.getPassword()));
			return new ResponseEntity<UserDto>(userDto,HttpStatus.FOUND);
		} else {
			throw new InvalidAuthTokenException(authTocken);
		}
	}

	@Override
	public ResponseEntity<Boolean> validateTocken(String authTocken) {

		authTocken = authTocken.split(" ")[1];
		try {
			String userName = jwtUtil.extractUsername(authTocken);
			//UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			/////////////// checking and getting Roles
			//Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
			//roles.stream().forEach(System.out::println);
			//java.util.Set<GrantedAuthority> userRoles = roles.stream().collect(Collectors.toSet());
			//userRoles.stream().forEach(System.out::println);
			//System.out.println(userRoles);
			//////////////////
			//if (Boolean.TRUE.equals(jwtUtil.validateToken(authTocken, userDetails))) {
			if (Boolean.TRUE.equals(jwtUtil.validateToken(authTocken, userName))) {
// 					ye check kr rha ki loggedout tocken me to ni h jo loggeout hote h wo blacklisted tocken me chala jata h 
//				LocalDateTime currentDateTime = LocalDateTime.now();
//				BlackListedToken blackListedToken = userLogoutMongoRepository.findByToken(authTocken);
//				if (blackListedToken != null && (currentDateTime.equals(blackListedToken.getExpirydate())
//						|| currentDateTime.isAfter(blackListedToken.getExpirydate()))) {
//					throw new InvalidAuthTokenException("You have intered invalid Auth Tocken : " + authTocken);
//				}
				 
			}
		} catch (Exception e) {
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<UserDto> createUser(UserDto userDto) {
		List<UserEntity> list = userRepository.findUserByUserName(userDto.getUsername());
		if (list.isEmpty()) {
			UserEntity userEntity = this.modelMapper.map(userDto, UserEntity.class);
			userEntity = userRepository.save(userEntity);
			userDto = this.modelMapper.map(userEntity, UserDto.class);
			return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
		} else {
			throw new UserNameAlreadyExistsException(userDto.getUsername());
		}
	}
	public String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
