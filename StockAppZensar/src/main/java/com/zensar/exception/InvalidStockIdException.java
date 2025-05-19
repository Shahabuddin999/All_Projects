package com.zensar.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStockIdException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InvalidStockIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidStockIdException() {
		super();
	}

	@Override
	public String toString() {
		return "InvalidStockIdException [message=" + message + "]";
	}
	
}
