package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.api.CourtApi;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.model.CityCourts;
import com.intuit.practice.courtbookingbackend.model.CourtsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.SUCCESS_STATUS;

@Service
public class CourtServicesImpl implements CourtServices {
    @Autowired
    private CourtApi courtApi;

    @Override
    public CourtsResponse getAllCourts() {
        List<CityCourts> cityCourts;
        try {
            cityCourts = courtApi.getAllCourts();
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all registerd users");
        }
        return new CourtsResponse(SUCCESS_STATUS, cityCourts);
    }
}
