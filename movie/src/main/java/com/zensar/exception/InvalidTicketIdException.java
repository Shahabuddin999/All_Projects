package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "resource not found")
public class InvalidTicketIdException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidTicketIdException() {
		this.message = "";
	}

	public InvalidTicketIdException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "The Ticket Id you have given that is not found " + this.message;
	}

}
