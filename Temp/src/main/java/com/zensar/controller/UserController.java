package com.zensar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.UserDTO;
import com.zensar.validationgroup.OnCreate;
import com.zensar.validationgroup.OnUpdate;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Validated(OnCreate.class) UserDTO user) {
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Validated(OnUpdate.class) UserDTO user) {
        return ResponseEntity.ok("User updated successfully");
    }
}
