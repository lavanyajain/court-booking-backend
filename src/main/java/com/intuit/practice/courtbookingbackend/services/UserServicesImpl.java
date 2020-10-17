package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.api.UserApi;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.SUCCESS_STATUS;

@Service
public class UserServicesImpl implements UsersServices {
/*
    @Autowired
    private UserApi userApi;

    public UsersResponse getAllUsers() {
        UsersResponse usersResponse;
        try {
            List<User> users = userApi.getAllUsers();
            usersResponse = new UsersResponse(SUCCESS_STATUS, users);
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all registerd users");
        }
        return usersResponse;
    }

    public UserResponse getUserByEmail(String email) {
        UserResponse userResponse;
        User user;
        try {
            user = userApi.getUserByEmail(email);
            userResponse = new UserResponse(null, SUCCESS_STATUS, user);
        }
        catch (UserNotFoundException exception) {
            throw new UserNotFoundException(exception.getMessage());
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all users data");
        }
        return userResponse;
    }**/
}
