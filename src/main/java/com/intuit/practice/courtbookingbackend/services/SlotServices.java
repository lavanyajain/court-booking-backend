package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.model.SlotModal;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface SlotServices {
    HashMap<String, List<SlotModal>> getAvailableSlots(Integer courtId);
}
