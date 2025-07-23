// src/main/java/com/example/demo/controller/UserController.java
package com.example.asynchronous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.example.asynchronous.entity.UserRequest;
import com.example.asynchronous.entity.UserResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private RestTemplate restTemplate;
	
	 @Autowired
	 private RestClient restClient;
	 
    @PostMapping("/{id}")
    public ResponseEntity<UserResponse> processUser(
            @PathVariable("id") Long userId,
            @RequestParam(name = "active", defaultValue = "false") boolean isActive,
            @RequestHeader(name = "Authorization", required = false) String jwtToken,
            @RequestBody UserRequest request
    ) {
        String message = String.format(
                "Received user: %s, age: %d, ID: %d, Active: %b, JWT: %s",
                request.getName(), request.getAge(), userId, isActive, jwtToken
        );
        UserResponse response = new UserResponse("success", message);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/callapiusingresttemplate")
    public UserResponse callUserApiUsingRestTemplate() {
        String url = "http://localhost:8080/api/users/100?active=true";

        UserRequest request = new UserRequest("Shahab", 30);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer mock-jwt-token-123");

        HttpEntity<UserRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<UserResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                UserResponse.class
        );

        return response.getBody();
    }
    
    @PostMapping("/callapiusingrestclient")
    public ResponseEntity<UserResponse> callUserApiUsingRestClient() {
        String url = "http://localhost:8080/api/users/100?active=true";

        UserRequest request = new UserRequest("Shahab", 30);

//        	UserResponse response = restClient.post()
//                .uri(url)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .header(HttpHeaders.AUTHORIZATION, "Bearer mock-jwt-token-123")
//                .body(request)
//                .retrieve()
//                .body(UserResponse.class); // This is returning without ResponseEntity
        
        
        ResponseEntity<UserResponse> response = restClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer mock-jwt-token-123")
                .body(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new RuntimeException("Client Error: " + res.getStatusCode()); // will execute if any client exception is occurred
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new RuntimeException("Server Error: " + res.getStatusCode());// will execute if any server exception is occurred
                })
                .toEntity(UserResponse.class); // This is returning with ResponseEntity from API call method
         
         return response;
    }
}
