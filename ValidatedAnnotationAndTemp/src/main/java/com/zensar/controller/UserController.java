package com.zensar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.configure.MyEmloyee;
import com.zensar.dto.User;
import com.zensar.dto.UserDTO;
import com.zensar.validationgroup.OnCreate;
import com.zensar.validationgroup.OnUpdate;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	MyEmloyee emp;
	
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Validated(OnCreate.class) UserDTO user) {
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Validated(OnUpdate.class) UserDTO user) {
        return ResponseEntity.ok("User updated successfully");
    }
    
    @PostMapping("/user")
    public ResponseEntity<User> user(@RequestBody User user) {
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/getflag")
    public int setFlag(){
    	return emp.flag;
    }
}
