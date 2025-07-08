package com.olx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity  // @EnableWebSecurity is combination of @Configuration thats why we are able to inject the of "public AuthenticationManager getAuthenticationManager()" in last method 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		// Second this method will be called at server starting time
	    http.csrf().disable()
	        .authorizeRequests()
	        
	        // ✅ Public or limited access routes
	        .antMatchers("/olxuser/user/authenticate").permitAll()  // login should be public
	        .antMatchers("/olxuser/token/validate").permitAll()
	        .antMatchers("/olxuser/change-password").permitAll()
	        .antMatchers("/olxuser/user/getUsername").permitAll()
	        .antMatchers(
	        	    "/v3/api-docs/**",
	        	    "/swagger-ui/**",
	        	    "/swagger-ui.html", // http://localhost:8080/swagger-ui
	        	    "/swagger-resources/**",
	        	    "/configuration/**",
	        	    "/webjars/**",
	        	    "/actuator/**", // http://localhost:8080/actuator/health
	        	    "/zipkin/**"  // to run zipkin, first run this command: D:\software>java -jar zipkin-server-2.23.16-exec.jar. then hit: http://localhost:9411/zipkin
	        	).permitAll()
	        
	        // ✅ Role-based access
	        .antMatchers("/olxuser/employee").hasRole("EMPLOYEE") // Save role in database like ROLE_EMPLOYEE or ROLE_ADMIN. but here you need only give ADMIN or EMPLOYEE without appending ROLE like ROLE_ADMIN or ROLE_EMPLOYEE etc.  because spring internally adds ROLE before ADMIN or EMPLOYEE
	        .antMatchers("/olxuser/testing").hasRole("ADMIN") // ✅ Corrected path
	        // Any other request needs to be authenticated
	        .anyRequest().authenticated() // ✅ All other endpoints require authentication
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    
	        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	        //Just above line Extracts the JWT from the Authorization header. Validates the token. Populates SecurityContext with the user's info and roles.

	        //.and().formLogin(); // can be removed if using pure JWT only
	}

	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// This belove line is for database call
		auth.userDetailsService(userDetailsService); // First this method will be called at server starting time
		
//		auth.inMemoryAuthentication()
//		.withUser("shahabuddin")
//		.password(passwordEncoder.encode("ansari"))
//		.roles("admin")
//		.and()
//		.withUser("nizam")
//		.password(passwordEncoder.encode("khan"))
//		.roles("user");
		
//		This is for inMemory		
		 
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager(); 
		// -> This getAuthenticationManager() method and AppConfig's class's method getModelMapper() and getPasswordEncoder() will be injected first because bean is injected at server starting time.
		// -> 1st call :"configure(AuthenticationManagerBuilder auth)" and then this method will call to
		// -> 2nd call :"configure(HttpSecurity http)"
	}
	
	
}
