package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.api.UserApi;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.model.User;
import com.intuit.practice.courtbookingbackend.model.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserApi services;

    @Override
    public UserResponse registerUser(User user) {
        UserResponse userResponse;
        try {
            userResponse = services.registerUser(user);
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all registered users");
        }
        return userResponse;
    }
}
