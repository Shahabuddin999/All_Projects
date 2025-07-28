package com.exception.customexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exception.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(RuntimeException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy:hh:mm:ss a")),
            request.getRequestURI() // It will return URI like : /api/users/5
            //request.getRequestURL().toString() // It will return complete URL like : http://localhost:8080/api/users/5
        );
        logger.error("Unhandled error at {}", request.getRequestURI(), ex); // Best practice is this line
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Something went wrong",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy:hh:mm:ss a")),
            request.getRequestURI()
        );
        logger.error("Unhandled error at {}", request.getRequestURI(), ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
