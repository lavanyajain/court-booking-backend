package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.model.BookingRequest;
import com.intuit.practice.courtbookingbackend.model.BookingResponse;

public interface BookingServices {
    BookingResponse bookSlot(BookingRequest bookingRequest);
}
