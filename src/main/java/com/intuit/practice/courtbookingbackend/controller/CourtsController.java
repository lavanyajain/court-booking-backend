package com.intuit.practice.courtbookingbackend.controller;

import com.intuit.practice.courtbookingbackend.exception.GetCourtsFailedException;
import com.intuit.practice.courtbookingbackend.model.Court;
import com.intuit.practice.courtbookingbackend.services.CourtServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class CourtsController {

    private static final Logger logger = LoggerFactory.getLogger(CourtsController.class);

    @Autowired
    private CourtServices services;

    @GetMapping(value = "/courts", produces = "application/json")
    public HashMap<String, HashMap<String, List<Court>>> getAllCourts() {
        HashMap<String, HashMap<String, List<Court>>> cityCourts = null;
        try {
            cityCourts = services.getAllCourts();
            return cityCourts;
        }
        catch (Exception exception) {
            logger.error("Error while fetching all registered users list with error {}", exception.getMessage());
            throw new GetCourtsFailedException(exception.getMessage());
        }
    }
}
