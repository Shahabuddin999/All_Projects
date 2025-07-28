package com.exception.customexception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    ResourceNotFoundException(){
    	super();
    }
}
