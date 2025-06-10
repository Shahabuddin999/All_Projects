package com.webflux.demo.dto;

public record UserDTO(Long id, String name) {
	public UserDTO() {
        this(0L, "Unknown");
    }
}
