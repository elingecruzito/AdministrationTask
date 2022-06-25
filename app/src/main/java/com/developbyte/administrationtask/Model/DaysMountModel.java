package com.developbyte.administrationtask.Model;

public class DaysMountModel {

    private int day;
    private String day_text;

    public DaysMountModel() {
    }

    public DaysMountModel(int day, String day_text) {
        this.day = day;
        this.day_text = day_text;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDay_text() {
        return day_text;
    }

    public void setDay_text(String day_text) {
        this.day_text = day_text;
    }
}
