package com.zensar.temp;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private int age;

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
