package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.exception.GetUsersFailedException;
import com.intuit.practice.courtbookingbackend.exception.UserNotFoundException;
import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.QueryExecutorResponse;
import com.intuit.practice.courtbookingbackend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private List<User> users;

    private User user;

    private String query;

    private ResultSet resultSet;

    private QueryExecutor queryExecutor = new QueryExecutor();

    private QueryExecutorResponse queryExecutorResponse;

    public List<User> getAllUsers() throws SQLException {
        try {
            users = new ArrayList<>();
            query = "select * from users";
            queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, query);
            resultSet = queryExecutorResponse.getResultSet();
            while(resultSet.next()){
                user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setEmailId(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                users.add(user);
            }
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", query, exception.getMessage());
            throw new GetUsersFailedException(exception.getMessage());
        }
        finally {
            queryExecutorResponse.getConnection().close();
            queryExecutorResponse.getResultSet().close();
            queryExecutorResponse.getStatement().close();
        }
        return users;
    }

    public User getUserByEmail(String emailId) throws SQLException{
        try {
            query = "select * from users";
            queryExecutorResponse = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, query);
            resultSet = queryExecutorResponse.getResultSet();
            while(resultSet.next()){
                if(resultSet.getString("email").equalsIgnoreCase(emailId)) {
                    user = new User();
                    user.setEmailId(emailId);
                    user.setFullName(resultSet.getString("full_name"));
                    user.setPhoneNumber(resultSet.getString("phone_number"));
                    user.setUserId(resultSet.getInt("user_id"));
                }
            }
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", query, exception.getMessage());
            throw new UserNotFoundException(exception.getMessage());
        }
        finally {
            queryExecutorResponse.getConnection().close();
            queryExecutorResponse.getResultSet().close();
            queryExecutorResponse.getStatement().close();
        }
        if(user == null) {
            throw new UserNotFoundException("Cannot not find entry for user in Registered users list");
        }
     return user;
    }
}
