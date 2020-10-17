package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.CityCourts;
import com.intuit.practice.courtbookingbackend.model.Court;
import com.intuit.practice.courtbookingbackend.model.CourtsResponse;
import com.intuit.practice.courtbookingbackend.model.QueryExecutorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    QueryExecutor queryExecutor = new QueryExecutor();

    public List<CityCourts> getAllCourts() throws SQLException {
        String sql = "select * from court";
        Court court;
        List<Court> courtList;
        CourtsResponse courtsResponse = new CourtsResponse();
        CityCourts cityCourts;
        List<CityCourts> cityCourts1 = new ArrayList<>();


        QueryExecutorResponse response = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sql);
        ResultSet resultSet = response.getResultSet();
        while (resultSet.next()) {
            cityCourts = new CityCourts();
            cityCourts.setCity(resultSet.getString("city"));
            sql = "select * from court where city='" + cityCourts.getCity() + "';";
            QueryExecutorResponse response1 = queryExecutor.executeQuery(JDBC_DRIVER, DB_URL, USER_NAME, PASSWORD, sql);
            ResultSet resultSet1 = response1.getResultSet();
            courtList = new ArrayList<>();
            while (resultSet1.next()) {
                court = new Court();
                court.setCourtName(resultSet1.getString("name"));
                court.setMin(resultSet1.getInt("min_cost"));
                court.setMax(resultSet1.getInt("max_cost"));
                courtList.add(court);
            }
            cityCourts.setCourts(courtList);
            cityCourts1.add(cityCourts);
        }
        return cityCourts1;
    }
}
