package com.intuit.practice.courtbookingbackend.model;

import java.sql.Time;

public class SlotModal {
    private String startTime;
    private String endTime;

    public SlotModal() {}

    public SlotModal(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        endTime = endTime;
    }
}
