package com.intuit.practice.courtbookingbackend.model;

public class User {
    private Integer userId;
    private String emailId;
    private String fullName;
    private String phoneNumber;

    public User() {}

    public User(Integer userId, String emailId, String fullName, String phoneNumber) {
        this.userId = userId;
        this.emailId = emailId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
