package com.redis.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.redis.service.SessionService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SessionService sessionService;

    public AuthController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username,
                                   @RequestParam String password) {
        String token = sessionService.login(username, password);
        if (token != null) {
            return ResponseEntity.ok("Login successful. Token: " + token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String token) {
        boolean result = sessionService.logout(token);
        if (result) {
            return ResponseEntity.ok("Logout successful");
        }
        return ResponseEntity.status(400).body("Invalid token");
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(@RequestParam String token) {
        boolean loggedIn = sessionService.isLoggedIn(token);
        return ResponseEntity.ok(loggedIn ? "User is logged in" : "User is not logged in");
    }
}
