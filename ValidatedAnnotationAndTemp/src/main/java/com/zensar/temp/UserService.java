package com.zensar.temp;
class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }
}

public class UserService {

    public void validateUser(String username){
        if (username == null || username.isEmpty()) {
            throw new InvalidUserException("Username cannot be empty");
        }
    }

    public static void main(String[] args) {
        UserService service = new UserService();
       // try {
            service.validateUser(""); // Invalid
//        } catch (InvalidUserException e) {
//            System.out.println("Caught: " + e.getMessage());
//        }
    }
}
