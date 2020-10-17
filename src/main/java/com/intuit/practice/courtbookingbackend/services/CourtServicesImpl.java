package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.api.CourtApi;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.model.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CourtServicesImpl implements CourtServices {
    @Autowired
    private CourtApi courtApi;

    @Override
    public HashMap<String, HashMap<String, List<Court>>> getAllCourts() {
        HashMap<String, HashMap<String, List<Court>>> cityCourts;
        try {
            cityCourts = courtApi.getCourtsByCity();
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all registerd users");
        }
        return cityCourts;
    }
}
