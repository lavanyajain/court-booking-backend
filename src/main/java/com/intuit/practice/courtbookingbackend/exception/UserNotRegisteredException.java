package com.intuit.practice.courtbookingbackend.exception;

public class UserNotRegisteredException extends RuntimeException {
  public UserNotRegisteredException(String message) {
      super(message);
  }
}
