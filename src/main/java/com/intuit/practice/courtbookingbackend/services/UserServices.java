package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.model.User;
import com.intuit.practice.courtbookingbackend.model.UserResponse;

public interface UserServices {
    UserResponse registerUser(User user);
}
