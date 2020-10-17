package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.api.CourtApi;
import com.intuit.practice.courtbookingbackend.api.UserApi;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.model.CourtsResponse;
import com.intuit.practice.courtbookingbackend.model.User;
import com.intuit.practice.courtbookingbackend.model.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.intuit.practice.courtbookingbackend.config.ConfigurationData.SUCCESS_STATUS;

public class CourtServicesImpl implements CourtServices {
    @Autowired
    private CourtApi courtApi;

    @Override
    public CourtsResponse getAllRegisteredCourts() {
        CourtsResponse courtsResponse;
        try {
            CourtsResponse courts = courtApi.getCourtsByAreaCode();
            usersResponse = new UsersResponse(SUCCESS_STATUS, users);
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all registerd users");
        }
        return usersResponse;
    }
}
