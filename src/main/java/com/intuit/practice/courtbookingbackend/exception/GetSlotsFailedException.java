package com.intuit.practice.courtbookingbackend.exception;

public class GetSlotsFailedException extends RuntimeException {
    public GetSlotsFailedException(String message) {
        super(message);
    }
}
