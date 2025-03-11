package com.augustojbe.demo_park_api.exception;

public class UsernameUniqueViolatioinException extends RuntimeException {

    public UsernameUniqueViolatioinException(String message) {
        super(message);

    }
}
