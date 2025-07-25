// src/main/java/com/example/demo/dto/UserResponse.java
package com.example.asynchronous.entity;

public class UserResponse {
    private String status;
    private String message;

    public UserResponse() {}
    public UserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
