package com.intuit.practice.courtbookingbackend.controller;

import com.intuit.practice.courtbookingbackend.exception.UserRegistrationException;
import com.intuit.practice.courtbookingbackend.model.User;
import com.intuit.practice.courtbookingbackend.model.UserResponse;
import com.intuit.practice.courtbookingbackend.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.FAILURE_STATUS;

@Controller
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices services;

    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<UserResponse> getAllUsers(@RequestBody User user) {
        UserResponse usersResponse;
        try {
            usersResponse = services.registerUser(user);
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error("Error while fetching all registerd users list with error {}", exception.getMessage());
            throw new UserRegistrationException(exception.getMessage());
        }
    }
}
