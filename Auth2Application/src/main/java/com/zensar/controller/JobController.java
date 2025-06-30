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

	// To logout hit http://localhost:8080/logout   then re-login via google/github
	@GetMapping(value="/")
    public Map<String, Object> showGoogle(OAuth2AuthenticationToken token, @AuthenticationPrincipal OAuth2User user) {
        return user.getAttributes();
    }
//	public String showMessage(Principal principal) {
//		OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) principal;
//		return "Hello : "+oAuth2AuthenticationToken.getPrincipal().getAttribute("login");
//	}
}
