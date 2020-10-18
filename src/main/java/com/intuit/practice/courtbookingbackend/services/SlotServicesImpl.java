package com.intuit.practice.courtbookingbackend.services;

import com.intuit.practice.courtbookingbackend.api.SlotApi;
import com.intuit.practice.courtbookingbackend.exception.QueryExecutionException;
import com.intuit.practice.courtbookingbackend.model.SlotModal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SlotServicesImpl implements SlotServices{

    @Autowired
    private SlotApi slotsApi;

    @Override
    public HashMap<String, List<SlotModal>> getAvailableSlots(Integer courtId) {
        HashMap<String, List<SlotModal>> slots;
        try {
            slots = slotsApi.getSlotByCourtId(courtId);
        }
        catch (Exception exception) {
            throw new QueryExecutionException("Error while fetching all registerd users");
        }
        return slots;
    }
}
