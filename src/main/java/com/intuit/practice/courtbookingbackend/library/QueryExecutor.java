package com.intuit.practice.courtbookingbackend.library;

import com.intuit.practice.courtbookingbackend.model.QueryExecutorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class QueryExecutor {

    private Connection conn = null;

    private Statement stmt = null;

    private String query;

    private ResultSet resultSet;

    private static final Logger logger = LoggerFactory.getLogger(QueryExecutor.class);

    public QueryExecutorResponse executeQuery(String jdbc_driver, String db_url, String username, String password, String query) throws SQLException {
        QueryExecutorResponse queryExecutorResponse = new QueryExecutorResponse();
        try {
            Class.forName(jdbc_driver);
            queryExecutorResponse.setConnection(DriverManager.getConnection(db_url, username, password));
            queryExecutorResponse.setStatement((queryExecutorResponse.getConnection().createStatement()));
            //query = "select * from USER";
            queryExecutorResponse.setResultSet(queryExecutorResponse.getStatement().executeQuery(query));
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", query, exception.getMessage());
        }
        return queryExecutorResponse;
    }

    public QueryExecutorResponse executeUpdate(String jdbc_driver, String db_url, String username, String password, String query) throws SQLException {
        QueryExecutorResponse queryExecutorResponse = new QueryExecutorResponse();
        try {
            Class.forName(jdbc_driver);
            queryExecutorResponse.setConnection(DriverManager.getConnection(db_url, username, password));
            queryExecutorResponse.setStatement((queryExecutorResponse.getConnection().createStatement()));
            queryExecutorResponse.getStatement().executeUpdate(query);
        }
        catch (Exception exception) {
            logger.error("Error while executing {} SQL query with the error message {}", query, exception.getMessage());
        }
        return queryExecutorResponse;
    }
}
