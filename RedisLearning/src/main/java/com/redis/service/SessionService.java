package com.redis.service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SessionService {

    private final RedisTemplate<String, Object> redisTemplate;

    public SessionService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Dummy login: accepts username/password
    public String login(String username, String password) {
        // In real app, validate from DB
    	try {
        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1234")) {
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(token, username, 30, TimeUnit.MINUTES); // token expires in 30 min
            return token;
        }
    	}catch(Exception e) {
    		System.out.println("Error: "+e.getMessage());
    	}
        return null;
    }

    public boolean logout(String token) {
        Boolean deleted = redisTemplate.delete(token);
        return deleted != null && deleted;
    }

    public boolean isLoggedIn(String token) {
        return redisTemplate.hasKey(token);
    }
}
