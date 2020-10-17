package com.intuit.practice.courtbookingbackend.exception;

public class GetUsersFailedException extends RuntimeException{
    public GetUsersFailedException(String message) {
        super(message);
    }
}
