package com.intuit.practice.courtbookingbackend.model;

import java.util.List;

public class CourtsResponse {
    private String status;
    private List<CityCourts> courts;

    public CourtsResponse() {
    }

    public CourtsResponse(String status, List<CityCourts> courts) {
        this.status = status;
        this.courts = courts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CityCourts> getCourts() {
        return courts;
    }

    public void setCourts(List<CityCourts> courts) {
        this.courts = courts;
    }
}
