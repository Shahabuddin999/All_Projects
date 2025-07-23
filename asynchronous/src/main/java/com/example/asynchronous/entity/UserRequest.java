// src/main/java/com/example/demo/dto/UserRequest.java
package com.example.asynchronous.entity;

public class UserRequest {
    private String name;
    private int age;

    // Constructors
    public UserRequest() {}
    public UserRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
