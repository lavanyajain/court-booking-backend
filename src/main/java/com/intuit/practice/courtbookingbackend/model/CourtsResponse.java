package com.intuit.practice.courtbookingbackend.model;

import java.util.HashMap;
import java.util.List;

public class CourtsResponse {
    HashMap<String, HashMap<String, List<Court>>>  response;

    public HashMap<String, HashMap<String, List<Court>>> getResponse() {
        return response;
    }

    public void setResponse(HashMap<String, HashMap<String, List<Court>>> response) {
        this.response = response;
    }
}
