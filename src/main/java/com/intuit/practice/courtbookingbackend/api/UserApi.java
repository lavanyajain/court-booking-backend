package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.exception.UserRegistrationException;
import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.QueryExecutorResponse;
import com.intuit.practice.courtbookingbackend.model.User;
import com.intuit.practice.courtbookingbackend.model.UserResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.SUCCESS_STATUS;

@Component
public class UserApi {
    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    private QueryExecutorResponse queryExecutorResponse;

    public UserResponse registerUser(User user) throws SQLException {
        String sqlQuery = null;
        QueryExecutor queryExecutor = new QueryExecutor();
        try {
            sqlQuery = "insert into user (user_id, email, full_name, phone_number) values (null, '" + user.getEmailId() + "', '" + user.getFullName() + "', '" + user.getPhoneNumber() + "');";
            queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sqlQuery);
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", sqlQuery, exception.getMessage());
            throw new UserRegistrationException(exception.getMessage());
        }
        finally {
            queryExecutorResponse.getConnection().close();
            queryExecutorResponse.getStatement().close();
        }
        return new UserResponse(SUCCESS_STATUS, user);
    }
}
