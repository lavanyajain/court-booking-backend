package com.intuit.practice.courtbookingbackend.model;

import java.util.List;

public class CourtsResponse {
    private String status;

    private List<Court> courts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Court> getCourts() {
        return courts;
    }

    public void setCourts(List<Court> courts) {
        this.courts = courts;
    }
}
