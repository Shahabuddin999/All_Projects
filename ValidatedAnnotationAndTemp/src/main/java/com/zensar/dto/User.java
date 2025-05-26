package com.zensar.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
    @JsonProperty("user_name") // renamed for JSON
    private String username;

    @JsonIgnore // completely ignored in JSON input/output. it won't take input directly from postman and won't give response to client(postman) 
    private String password;
   
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // it will take input from postman but won't response to client(postman)
    private String secretTocken;
    
    private String email;
    
}
