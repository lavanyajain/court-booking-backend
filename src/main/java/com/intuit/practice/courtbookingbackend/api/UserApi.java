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

    private String sqlQuery = null;

    private QueryExecutor queryExecutor = new QueryExecutor();

    public UserResponse registerUser(User user) throws SQLException {

        try {
            sqlQuery = "insert into user (email, full_name, phone_number) values ('" + user.getEmailId() + "', '" + user.getFullName() + "', '" + user.getPhoneNumber() + "');";
            queryExecutor.executeUpdate(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sqlQuery);
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", sqlQuery, exception.getMessage());
            throw new UserRegistrationException(exception.getMessage());
        }
        Integer userId = fetchUserId(user.getEmailId());
        user.setUserId(userId);
        return new UserResponse(SUCCESS_STATUS, user);
    }

    private Integer fetchUserId(String email) throws SQLException {
        Integer userId = null;
        try {
            sqlQuery = "select user_id from user where email='" + email + "'";
            queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sqlQuery);
            ResultSet resultSet = queryExecutorResponse.getResultSet();
            while (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
        }
        catch (Exception exception) {
            logger.error("Error while fetching new registered user id");
            throw new UserRegistrationException(exception.getMessage());
        }
        finally {
            queryExecutorResponse.getConnection().close();
            queryExecutorResponse.getStatement().close();
        }
        return userId;
    }
}
