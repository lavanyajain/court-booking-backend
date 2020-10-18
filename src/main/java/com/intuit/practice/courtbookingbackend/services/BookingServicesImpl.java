package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.api.BookingApi;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.model.BookingRequest;
import com.intuit.practice.courtbookingbackend.model.BookingResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServicesImpl implements BookingServices {
    @Autowired
    private BookingApi bookingApi;

    @Override
    public BookingResponse bookSlot(BookingRequest bookingRequest) {
        BookingResponse bookingResponse;
        try {
            bookingResponse = bookingApi.bookCourt(bookingRequest);
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all registerd users");
        }
        return bookingResponse;
    }
}
