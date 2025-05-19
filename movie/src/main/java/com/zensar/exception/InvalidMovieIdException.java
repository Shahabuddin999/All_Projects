package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "resource not found")
public class InvalidMovieIdException extends RuntimeException {
	private String message;

	public InvalidMovieIdException() {
		this.message = "";
	}

	public InvalidMovieIdException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.message;
	}

}