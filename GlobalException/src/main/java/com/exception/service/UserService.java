package com.exception.service;


import org.springframework.stereotype.Service;

import com.exception.customexception.ResourceNotFoundException;

@Service
public class UserService {
    public String getUserById(Long id) {
        // Simulate not found
        if (id != 1L) {
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
        return "User found!";
    }
}
