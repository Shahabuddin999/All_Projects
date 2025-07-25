package com.olx.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

	 public static final String passwordProtected = "[password_protected]";
	@Override
	public ResponseEntity<Boolean> userLogout(String authToken) {
		
		String token = authToken.split(" ")[1];
		try {
		Date extractExpiration = jwtUtil.extractExpiration(token);
		LocalDateTime expiryDateTime = extractExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		BlackListedToken balBlackListedToken = new BlackListedToken(1, token, LocalDateTime.now(), expiryDateTime);
		userLogoutMongoRepository.save(balBlackListedToken);
		return new ResponseEntity<>(true,HttpStatus.FOUND);
		} catch (Exception e) {
			throw new InvalidAuthTokenException("You have intered invalid Auth Thocken");
		}
	}

	@Override
	public ResponseEntity<UserDto> getUser(String authTocken, String userName) {
		List<UserEntity> list = userRepository.findUserByUserName(userName);
		if (!list.isEmpty()) {
			UserDto userDto = this.modelMapper.map(list.get(0), UserDto.class);
			userDto.setPassword(setPasswordForDto());
			return new ResponseEntity<>(userDto,HttpStatus.FOUND);
		} else {
			throw new InvalidAuthTokenException(authTocken);
		}
	}

	@Override
	public ResponseEntity<Boolean> validateTocken(String authTocken) {

		//authTocken = authTocken.split(" ")[1];
		try {
			String userName = jwtUtil.extractUsername(authTocken);
			System.out.println("UserName: "+userName);
		} catch (Exception e) {
			throw new InvalidAuthTokenException("You have intered invalid Auth Tocken");
		}
		return new ResponseEntity<>(true, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<UserDto> createUser(UserDto userDto) {
		List<UserEntity> list = userRepository.findUserByUserName(userDto.getUsername());
		if (list.isEmpty()) {
			UserEntity userEntity = this.modelMapper.map(userDto, UserEntity.class);
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
			userEntity = userRepository.save(userEntity);
			userDto = this.modelMapper.map(userEntity, UserDto.class);
			userDto.setPassword(setPasswordForDto());
			return new ResponseEntity<>(userDto,HttpStatus.CREATED);
		} else {
			throw new UserNameAlreadyExistsException(userDto.getUsername());
		}
	}
	public String encodePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public ResponseEntity<UserDto> changePassword(UserDto userDto) {
		List<UserEntity> userList = userRepository.findByUsername(userDto.getUsername());

        if (userList.isEmpty()) {
        	throw new InvalidUserNameOrPasswordException("You have passed invalid User Name: "+userDto.getUsername());
        }

        UserEntity userEntity = userList.get(0);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        UserDto responseDto = new UserDto();
        responseDto = this.modelMapper.map(userEntity, UserDto.class);
        responseDto.setPassword("Password Updated Successfully");

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	public static String setPasswordForDto() {
		return passwordProtected;
	}
}
