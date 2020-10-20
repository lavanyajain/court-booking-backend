package com.intuit.practice.courtbookingbackend.api;

import com.intuit.practice.courtbookingbackend.exception.GetSlotsFailedException;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.library.QueryExecutor;
import com.intuit.practice.courtbookingbackend.model.QueryExecutorResponse;
import com.intuit.practice.courtbookingbackend.model.SlotModal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import java.util.*;

@Component
public class SlotApi {
    @Value("${court.booking.database.driver}")
    private String JDBC_DRIVER;

    @Value("${court.booking.database.url}")
    private String DB_URL;

    @Value("${court.booking.database.username}")
    private String USER_NAME;

    @Value("${court.booking.database.password}")
    private String PASSWORD;

    private static final Logger logger = LoggerFactory.getLogger(SlotApi.class);

    private final QueryExecutor queryExecutor = new QueryExecutor();

    private ResultSet getAllAvailableSlots(Integer courtId) {
        String sqlQuery = "select * from slots where court_id=" + courtId + ";";
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
            throw new GetSlotsFailedException(exception.getMessage());
        }
        return queryExecutorResponse.getResultSet();
    }

    public HashMap<String, List<SlotModal>> getSlotByCourtId(Integer courtId) throws SQLException {
        ResultSet resultSet = getAllAvailableSlots(courtId);
        HashMap<String, List<SlotModal>> slotsAvailable = new HashMap<>();
        List<SlotModal> slots = new ArrayList<>();
        while (resultSet.next()) {
            Timestamp startTime = resultSet.getTimestamp("start_time");
            Timestamp endTime = resultSet.getTimestamp("end_time");
            String status = resultSet.getString("status");
            String slotDate = new Date(startTime.getTime()).toString().substring(0,10);
            if(startTime.compareTo(new Timestamp(System.currentTimeMillis())) > 0) {
                if(!slotsAvailable.containsKey(slotDate))
                    slotsAvailable.put(slotDate, new ArrayList<>());
                slots = slotsAvailable.get(slotDate);
                String start = new Time(startTime.getTime()).toString();
                String end = new Time(endTime.getTime()).toString();
                slots.add(new SlotModal(start, end, status));
            }
            slotsAvailable.put(slotDate, slots);
        }
        return slotsAvailable;
    }
}
