package com.intuit.practice.courtbookingbackend.controller;

import com.intuit.practice.courtbookingbackend.model.Court;
import com.intuit.practice.courtbookingbackend.model.CourtsResponse;
import com.intuit.practice.courtbookingbackend.services.CourtServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.FAILURE_STATUS;

@Controller
@RestController
@RequestMapping("/api")
public class CourtsController {
    private static final Logger logger = LoggerFactory.getLogger(CourtsController.class);

    @Autowired
    private CourtServices services;

    @GetMapping(value = "/courts", produces = "application/json")
    public ResponseEntity<CourtsResponse> getAllUsers() {
        CourtsResponse courtsResponse = null;
        try {
            courtsResponse = services.getAllCourts();
            return new ResponseEntity<>(courtsResponse, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error("Error while fetching all registerd users list with error {}", exception.getMessage());
            return new ResponseEntity<>(courtsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/map", produces = "application/json")
    public Map<String, List<Court>> getMap() {
        List<Court> courtList = new ArrayList<>();
        Map<String, List<Court>> res = new HashMap<>();
        courtList.add(new Court("Playoo", 100, 1000));
        courtList.add(new Court("Playoo", 100, 1000));
        res.put("Bangalore", courtList);
        return res;
    }
}
