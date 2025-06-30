package com.zensar.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @GetMapping("/test")
    public String showTest() {
        return "Testing...";
    }
    
    // To logout you need hit http://localhost:8080/logout then you current session will be logged out and login via github or google
    @GetMapping("/google")
    public Map<String, Object> showGoogle(OAuth2AuthenticationToken token, @AuthenticationPrincipal OAuth2User user) {
        if (!token.getAuthorizedClientRegistrationId().equals("google")) {
            throw new RuntimeException("Not logged in with Google");
        }
        return user.getAttributes();
    }

    @GetMapping("/github")
    public Map<String, Object> showGithub(OAuth2AuthenticationToken token, @AuthenticationPrincipal OAuth2User user) {
        if (!token.getAuthorizedClientRegistrationId().equals("github")) {
            throw new RuntimeException("Not logged in with GitHub");
        }
        return user.getAttributes();
    }
}
