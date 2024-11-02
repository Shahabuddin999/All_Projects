package com.olx.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.entity.UserEntity;
import com.olx.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> userEntities = userRepo.findByUsername(username);
		if(userEntities.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = userEntities.get(0);
		Collection<GrantedAuthority> authority = new ArrayList<>();
		String[] roles = userEntity.getRoles().split(",");
		for(int i=0; i<roles.length; i++)
			authority.add(new SimpleGrantedAuthority(roles[i]));
		UserDetails userDetails = new User(username, passwordEncoder.encode(userEntity.getPassword()), authority);
		boolean check = passwordEncoder.matches(userEntity.getPassword(), passwordEncoder.encode(userEntity.getPassword()));
		if(check) 
			System.out.println("successfully validated...");
		return userDetails;
	}
}
