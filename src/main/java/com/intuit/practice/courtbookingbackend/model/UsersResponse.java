package com.intuit.practice.courtbookingbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class UsersResponse {
    @JsonIgnore
    private String status;

    @JsonIgnore
    private String message;

    @JsonIgnore
    private List<User> users;

    UsersResponse() { }

    public UsersResponse(String status, List<User> users) {
        this.status = status;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
