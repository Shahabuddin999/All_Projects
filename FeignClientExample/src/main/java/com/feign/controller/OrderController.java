package com.feign.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feign.dto.UserDTO;
import com.feign.feighinterface.UserClient;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final UserClient userClient;

    public OrderController(UserClient userClient) {
        this.userClient = userClient;
    }

    @PutMapping("/update-user/{userId}")
    public String updateUser(
            @PathVariable Long userId,
            @RequestParam boolean sendEmail,
            @RequestBody UserDTO user,
            @RequestHeader("request-id") String requestId
    ) {
        return userClient.updateUserById(userId, sendEmail, user, "REQ-555-XYZ"); // ✅ sending header
    }
}
