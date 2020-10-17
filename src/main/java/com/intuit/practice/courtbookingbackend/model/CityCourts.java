package com.intuit.practice.courtbookingbackend.model;

import java.util.List;

public class CityCourts {
    private String city;
    private List<Court> courts;

    public CityCourts() {}

    public CityCourts(String city, List<Court> courts) {
        this.city = city;
        this.courts = courts;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Court> getCourts() {
        return courts;
    }

    public void setCourts(List<Court> courts) {
        this.courts = courts;
    }
}
