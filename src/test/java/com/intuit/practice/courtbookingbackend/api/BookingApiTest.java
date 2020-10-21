package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.model.BookingRequest;
import com.intuit.practice.courtbookingbackend.model.BookingResponse;
import com.intuit.practice.courtbookingbackend.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookingApiTest {

    @InjectMocks
    private BookingApi bookingApi = spy(new BookingApi());

    @Mock
    private BookingRequest bookingRequest;

    private HashMap<String, User> users = new HashMap<>();

    @Mock
    private ResultSet resultSet;

    @Mock
    private User user;

    @Mock
    private BookingResponse bookingResponse;

    @Test
    public void validateBookCourt() throws SQLException {
        when(bookingRequest.getCourtId()).thenReturn(1);
        when(bookingRequest.getEmail()).thenReturn("abc@gmail.com");
        when(bookingRequest.getEndTime()).thenReturn("2020-10-19 06:00:00");
        when(bookingRequest.getStartTime()).thenReturn("2020-10-19 05:00:00");
        when(bookingRequest.getFullName()).thenReturn("lavanya");
        when(bookingRequest.getPhoneNumber()).thenReturn("98767890");
        doReturn(true).doReturn(false).when(resultSet).next();
        users.put("abc@gmail.com", user);
        doReturn(users).when(bookingApi).getAllUsers(any());
        doReturn(bookingResponse).when(bookingApi).bookSlotIfAvailable(any(), any(), any());
        doReturn("successful").when(bookingResponse).getStatus();

        assertThat(bookingResponse.getStatus().equals("successful"));
    }
}
