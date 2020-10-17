package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.model.Court;

import java.util.HashMap;
import java.util.List;

public interface CourtServices {
    HashMap<String, HashMap<String, List<Court>>> getAllCourts();
}
