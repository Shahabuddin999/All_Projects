package com.feign.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feign.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @RequestParam boolean sendEmail,
            @RequestBody UserDTO user,
            @RequestHeader("X-Request-ID") String requestId // ✅ RECEIVED HERE
    ) {
        System.out.println("========= RECEIVED UPDATE REQUEST =========");
        System.out.println("UserID          : " + id);
        System.out.println("Send Email      : " + sendEmail);
        System.out.println("UserDTO         : " + user);
        System.out.println("X-Request-ID    : " + requestId); // ✅ LOGGED FROM HEADER
        System.out.println("===========================================");

        return ResponseEntity.ok("User " + id + " updated. Email sent: " + sendEmail);
    }
}
