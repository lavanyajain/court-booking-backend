package com.intuit.practice.courtbookingbackend.controller;

import com.intuit.practice.courtbookingbackend.exception.GetCourtsFailedException;
import com.intuit.practice.courtbookingbackend.model.BookingRequest;
import com.intuit.practice.courtbookingbackend.model.BookingResponse;
import com.intuit.practice.courtbookingbackend.model.Court;
import com.intuit.practice.courtbookingbackend.services.BookingServices;
import com.intuit.practice.courtbookingbackend.services.CourtServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingServices services;

    @PostMapping(value = "/book", produces = "application/json", consumes = "application/json")
    BookingResponse bookCourt(@RequestBody BookingRequest bookingRequest) {
        BookingResponse bookingResponse = null;
        try {
            bookingResponse = services.bookSlot(bookingRequest);
            return bookingResponse;
        }
        catch (Exception exception) {
            logger.error("Error while fetching all registered users list with error {}", exception.getMessage());
            throw new GetCourtsFailedException(exception.getMessage());
        }
    }
}
