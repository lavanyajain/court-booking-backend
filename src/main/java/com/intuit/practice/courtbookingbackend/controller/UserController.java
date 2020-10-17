package com.intuit.practice.courtbookingbackend.controller;

import com.intuit.practice.courtbookingbackend.exception.GetUsersFailedException;
import com.intuit.practice.courtbookingbackend.exception.UserNotFoundException;
import com.intuit.practice.courtbookingbackend.model.UserResponse;
import com.intuit.practice.courtbookingbackend.model.UsersResponse;
import com.intuit.practice.courtbookingbackend.services.UsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.FAILURE_STATUS;

@Controller
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UsersServices services;

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<UsersResponse> getAllUsers() {
        UsersResponse usersResponse = null;
        try {
            usersResponse = services.getAllUsers();
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error("Error while fetching all registerd users list with error {}", exception.getMessage());
            usersResponse.setMessage(FAILURE_STATUS);
            usersResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(usersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/{emailId}", produces = "application/json")
    public ResponseEntity<UserResponse> getUserWithId(@PathVariable String emailId) {
        UserResponse userResponse = null;
        try {
            userResponse = services.getUserByEmail(emailId);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        catch (UserNotFoundException exception) {
            logger.error("User with {} doesn't exist", emailId);
            return new ResponseEntity<>(new UserResponse(exception.getMessage(), FAILURE_STATUS, null), HttpStatus.NOT_FOUND);
        }
        catch (Exception exception) {
            logger.error("Error while executing SQL query {}", exception.getMessage());
            return new ResponseEntity<>(new UserResponse(exception.getMessage(), FAILURE_STATUS, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
