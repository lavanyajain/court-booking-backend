package com.intuit.practice.courtbookingbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserResponse {
    @JsonIgnore
    private String message;

    @JsonIgnore
    private String status;

    @JsonIgnore
    private User user;

    UserResponse() {}

    public UserResponse(String message, String status, User user) {
        this.message = message;
        this.status = status;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
