package com.intuit.practice.courtbookingbackend.model;

import java.sql.Time;

public class SlotModal {
    private String startTime;
    private String endTime;
    private String status;


    public SlotModal() {}

    public SlotModal(String startTime, String endTime, String status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
