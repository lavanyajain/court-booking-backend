package com.intuit.practice.courtbookingbackend.exception;

public class GetCourtsFailedException extends RuntimeException{
    public GetCourtsFailedException(String message) {
        super(message);
    }
}