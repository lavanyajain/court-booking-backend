package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.model.UserResponse;
import com.intuit.practice.courtbookingbackend.model.UsersResponse;

public interface UsersServices {
    UsersResponse getAllUsers();
    UserResponse getUserByEmail(String emailId);
}
