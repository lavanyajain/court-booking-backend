package com.intuit.practice.courtbookingbackend.exception;

public class QueryExecutionException extends RuntimeException{
    public QueryExecutionException(String message) {
        super(message);
    }
}
