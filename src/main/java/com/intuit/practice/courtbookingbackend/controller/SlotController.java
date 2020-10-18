package com.intuit.practice.courtbookingbackend.controller;

import com.intuit.practice.courtbookingbackend.exception.GetCourtsFailedException;
import com.intuit.practice.courtbookingbackend.model.SlotModal;
import com.intuit.practice.courtbookingbackend.services.SlotServices;

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

import java.util.HashMap;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class SlotController {
    private static final Logger logger = LoggerFactory.getLogger(SlotController.class);

    @Autowired
    private SlotServices services;

    @GetMapping(value = "/slots/{courtId}", produces = "application/json")
    public ResponseEntity<HashMap<String, List<SlotModal>>> getAvailableSlots(@PathVariable Integer courtId) {
        HashMap<String, List<SlotModal>> slots = null;
        try {
            slots = services.getAvailableSlots(courtId);
            return new ResponseEntity<>(slots, HttpStatus.OK);
        }
        catch (Exception exception) {
            logger.error("Error while fetching all available slots for courtId {} with {} error", courtId, exception.getMessage());
            throw new GetCourtsFailedException(exception.getMessage());
        }
    }
}
