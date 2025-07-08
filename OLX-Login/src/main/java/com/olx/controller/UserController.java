package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.olx.dto.UserDto;
import com.olx.exception.InvalidAuthTokenException;
import com.olx.exception.InvalidUserNameOrPasswordException;
import com.olx.security.JwtUtil;
import com.olx.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/olxuser")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Operation(summary = "Authenticate User", description = "Validate username and password, return JWT token")
    @PostMapping(value = "/user/authenticate", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> authenticate(
            @Parameter(description = "User DTO with credentials", required = true) @RequestBody UserDto userDto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())); // This line will call loadUserByUsername(String username)
            System.out.println("Auth: " + auth);
        } catch (Exception e) {
            throw new InvalidUserNameOrPasswordException("You have entered invalid username or password");
        }
        String authToken = jwtUtil.generateToken(userDto.getUsername());
        return new ResponseEntity<>(authToken, HttpStatus.OK);
    }

    @Operation(summary = "Logout user", description = "Invalidate the user token")
    @DeleteMapping(value = "/user/logout")
    public ResponseEntity<Boolean> userLogout(
            @Parameter(description = "Auth token", required = true) @RequestHeader("Authorization") String authorization) {
        return userService.userLogout(authorization);
    }

    @Operation(summary = "Create user", description = "Register a new user")
    @PostMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserDto> createUser(
            @Parameter(description = "User data", required = true) @RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Get current user info", description = "Returns authenticated user data")
    @GetMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserDto> getUser(@Parameter(hidden = true) Authentication authorization) {
        ResponseEntity<UserDto> user = null;
        ResponseEntity<Boolean> flag = validateUserTocken(authorization);
        if (Boolean.TRUE.equals(flag.getBody())) {
            String userName = authorization.getName();
            String token = getTocken();
            user = userService.getUser(token, userName);
        }
        return user;
    }

    @Operation(summary = "Validate JWT token", description = "Check if token is valid and authorized")
    @GetMapping(value = "/token/validate", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boolean> validateUserTocken(@Parameter(hidden = true) Authentication authorization) {
        authorization.getAuthorities().forEach(a -> System.out.println("✅ Role: " + a.getAuthority()));
        String token = getTocken();
        return userService.validateTocken(token);
    }

    @Operation(summary = "Get username from token", description = "Extract username from JWT token")
    @GetMapping(value = "/user/getUsername", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> getUsername(@Parameter(hidden = true) Authentication authorization) {
        String userName;
        try {
            String actualToken = getTocken();
            userService.validateTocken(actualToken);
            userName = jwtUtil.extractUsername(actualToken);
        } catch (Exception e) {
            throw new InvalidAuthTokenException("You have entered an invalid auth token");
        }
        return new ResponseEntity<>(userName, HttpStatus.FOUND);
    }

    @Operation(summary = "Admin test endpoint", description = "Returns message for ADMIN users only")
    @GetMapping(value = "/testing", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> testing(@Parameter(hidden = true) Authentication auth) {
        System.out.println("✅ Authenticated User: " + auth.getName());
        auth.getAuthorities().forEach(a -> System.out.println("✅ Role: " + a.getAuthority()));
        return ResponseEntity.ok("Hello ADMIN, you are authorized!");
    }

    @Operation(summary = "Change or encode password", description = "Encode the user password before saving to DB")
    @PostMapping(value = "/change-password", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserDto> encodePassword(@RequestBody UserDto userDto) {
        return userService.changePassword(userDto);
    }
    
    public String getTocken() {
    	return (String) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
