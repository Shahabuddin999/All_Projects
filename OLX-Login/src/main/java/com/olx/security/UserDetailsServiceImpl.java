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
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		List<UserEntity> userEntities = userRepo.findByUsername(username);
//		if(userEntities.isEmpty()) {
//			throw new UsernameNotFoundException(username);
//		}
//		UserEntity userEntity = userEntities.get(0);
//		Collection<GrantedAuthority> authority = new ArrayList<>();
//		String[] roles = userEntity.getRoles().split(",");
//		for(int i=0; i<roles.length; i++)
//			authority.add(new SimpleGrantedAuthority(roles[i]));
//		UserDetails userDetails = new User(username, passwordEncoder.encode(userEntity.getPassword()), authority);
//		boolean check = passwordEncoder.matches(userEntity.getPassword(), passwordEncoder.encode(userEntity.getPassword()));
//		if(check) 
//			System.out.println("successfully validated...");
//		return userDetails;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// This method will be called at login time if login success then you will get authTocken else will get "UsernameNotFoundException("User not found: " + username)"
	    List<UserEntity> userEntities = userRepo.findByUsername(username);
	    
	    if (userEntities.isEmpty()) {
	        throw new UsernameNotFoundException("User not found: " + username);
	    }

	    UserEntity userEntity = userEntities.get(0);

	    Collection<GrantedAuthority> authorities = new ArrayList<>();
	    String[] roles = userEntity.getRoles().split(",");
	    for (String role : roles) {
	        authorities.add(new SimpleGrantedAuthority(role.trim()));
	    }

	    // Spring Security will internally do: passwordEncoder.matches(rawPasswordFromLoginForm, encodedPasswordFromDB);
	    // UserDetails userDetails = new User(username, passwordEncoder.encode(userEntity.getPassword()), authorities); 
	    // Use just above line if your password id saved as a plain text not in encoded form

	    UserDetails userDetails = new User(username, userEntity.getPassword(), authorities);
	    // Use just above line if password is saved in database in encoded form so here you don't need to encode it again, because you are already getting password in encoded password form from database 
	    // passwordEncoder.matches(rawPasswordFromLoginForm, encodedPasswordFromDB); this line will execute latter just after above line, this line will match your given password by postman with database password. So we don't need to write it manually/explicitly. it is called internally by spring security. 
	    return userDetails;
	}

}
