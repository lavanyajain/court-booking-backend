package com.intuit.practice.courtbookingbackend.controller;

import com.intuit.practice.courtbookingbackend.model.CourtsResponse;
import com.intuit.practice.courtbookingbackend.model.UsersResponse;
import com.intuit.practice.courtbookingbackend.services.UsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.FAILURE_STATUS;

@RequestMapping("/api")
public class CourtsController {
    private static final Logger logger = LoggerFactory.getLogger(CourtsController.class);

    @Autowired
    private CourtsService services;

    @GetMapping(value = "/courts", produces = "application/json")
    public ResponseEntity<CourtsResponse> getAllUsers() {
        CourtsResponse courtsResponse = null;
        try {
            courtsResponse = services.getAllUsers();
            return new ResponseEntity<>(usersResponse, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error("Error while fetching all registerd users list with error {}", exception.getMessage());
            usersResponse.setMessage(FAILURE_STATUS);
            usersResponse.setMessage(exception.getMessage());
            return new ResponseEntity<>(usersResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
