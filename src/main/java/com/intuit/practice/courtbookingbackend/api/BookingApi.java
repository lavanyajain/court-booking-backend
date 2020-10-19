package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.exception.GetCourtsFailedException;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.exception.UserNotRegisteredException;
import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.FAILURE_STATUS;
import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.SUCCESS_STATUS;

@Component
public class BookingApi {
    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(BookingApi.class);

    private final QueryExecutor queryExecutor = new QueryExecutor();

    private QueryExecutorResponse queryExecutorResponse;

    private ResultSet getAllUsersFromTable() {
        String sqlQuery = "select * from user";
        try {
            queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sqlQuery);
        }
        catch (QueryExecutionException exception) {
            logger.error("Error while fetching available courts form DB validate DB configuration");
            throw new QueryExecutionException(exception.getMessage());
        }
        catch(Exception exception) {
            logger.error("Execution failed with {} error", exception.getMessage());
            throw new GetCourtsFailedException(exception.getMessage());
        }
        return queryExecutorResponse.getResultSet();
    }

    private HashMap<String, User> getAllUsers(ResultSet resultSet) throws SQLException {
        HashMap<String, User> users = new HashMap<>();
        User user;
        while (resultSet.next()) {
            user = new User();
            user.setEmailId(resultSet.getString("email").toLowerCase());
            user.setFullName(resultSet.getString("full_name"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setUserId(resultSet.getInt("user_id"));
            users.put(user.getEmailId(), user);
        }
        return users;
    }

    private boolean isUserRegistered(HashMap<String, User> users, String email) {
        return users.containsKey(email);
    }

    private ResultSet getAvailableSlots(Integer courtId) {
        String sqlQuery = "select * from slots where court_id=" + courtId + " AND status='Available';";
        try {
            queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sqlQuery);
        }
        catch (QueryExecutionException exception) {
            logger.error("Error while fetching available courts form DB validate DB configuration");
            throw new QueryExecutionException(exception.getMessage());
        }
        catch(Exception exception) {
            logger.error("Execution failed with {} error", exception.getMessage());
            throw new GetCourtsFailedException(exception.getMessage());
        }
        return queryExecutorResponse.getResultSet();
    }

    private BookingResponse markSlotAsBooked(Integer slotId, BookingRequest bookingRequest, User user) {
        String sqlQuery = "UPDATE slots SET status='Not Available', user_id=" + user.getUserId() + " WHERE slot_id=" + slotId + ";";
        try {
            queryExecutor.executeUpdate(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sqlQuery);
        }
        catch (Exception exception) {
            logger.error("Error while fetching available courts form DB validate DB configuration");
            throw new QueryExecutionException(exception.getMessage());
        }
        String message = "Booked successfully";
        return new BookingResponse(SUCCESS_STATUS, message, slotId, new SlotModal(bookingRequest.getStartTime(), bookingRequest.getEndTime()),user, bookingRequest.getCourtId());
    }

    private BookingResponse bookSlotIfAvailable(ResultSet resultSet, BookingRequest bookingRequest, User user) throws SQLException {
        BookingResponse bookingResponse = new BookingResponse();
        while (resultSet.next()) {
            Timestamp startTime = resultSet.getTimestamp("start_time");
            Timestamp endTime = resultSet.getTimestamp("end_time");
            Timestamp requestedStartTime = Timestamp.valueOf(bookingRequest.getStartTime());
            Timestamp requestedEndTime = Timestamp.valueOf(bookingRequest.getEndTime());
            if(startTime.compareTo(requestedStartTime) >= 0 && endTime.compareTo(requestedEndTime) <= 0) {
                 bookingResponse = markSlotAsBooked(resultSet.getInt("slot_id"), bookingRequest, user);
            }
        }
        if(bookingResponse.getBookingId() == null) {
            String message = "Not slots are available in the given range, Please check available slots and try again";
            return new BookingResponse(FAILURE_STATUS, message, null, new SlotModal(bookingRequest.getStartTime(), bookingRequest.getEndTime()),user, bookingRequest.getCourtId());
        }
        return bookingResponse;
    }

    public BookingResponse bookCourt(BookingRequest bookingRequest) throws SQLException {
        HashMap<String, User> users = getAllUsers(getAllUsersFromTable());
        if(! isUserRegistered(users, bookingRequest.getEmail().toLowerCase())) {
                throw new UserNotRegisteredException("User Not registered. Please register and try again to book a slot");
        }
        return bookSlotIfAvailable(getAvailableSlots(bookingRequest.getCourtId()), bookingRequest, users.get(bookingRequest.getEmail().toLowerCase()));
    }
}
