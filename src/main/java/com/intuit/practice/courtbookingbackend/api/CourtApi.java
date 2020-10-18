package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.exception.GetCourtsFailedException;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CourtApi {
    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(CourtApi.class);

    private final QueryExecutor queryExecutor = new QueryExecutor();

    private ResultSet getEntriesOfCourtTable() {
        String sqlQuery = "select * from court";
        QueryExecutorResponse queryExecutorResponse;
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

    public HashMap<String,HashMap<String, List<Court>>> getCourtsByCity() throws SQLException {
        String city, game;
        Court court;
        ResultSet resultSet = getEntriesOfCourtTable();
        HashMap<String, HashMap<String, List<Court>>> courts = new HashMap<>();
        while (resultSet.next()) {
            city = resultSet.getString("city");
            game = resultSet.getString("game");
            court = new Court(resultSet.getString("name"), resultSet.getInt("min_cost"), resultSet.getInt("max_cost"));
            if(!courts.containsKey(city)) {
                HashMap<String, List<Court>> hashMap = new HashMap<>();
                courts.put(city, hashMap);
            }
            HashMap<String, List<Court>> courtsEntry = courts.get(city);
            if(!courtsEntry.containsKey(game)) {
              courtsEntry.put(game, new ArrayList<>());
            }
            List<Court> c = courtsEntry.get(game);
            c.add(court);
            courtsEntry.put(game, c);
            courts.put(city, courtsEntry);
        }
    return courts;
    }
}
