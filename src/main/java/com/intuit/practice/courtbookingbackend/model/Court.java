package com.intuit.practice.courtbookingbackend.model;

public class Court {
    private String courtName;
    private String game;
    private Integer min;
    private Integer max;

    public Court() {}

    public Court(String courtName, int min, int max, String game) {
        this.courtName = courtName;
        this.min = min;
        this.max = max;
        this.game = game;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
