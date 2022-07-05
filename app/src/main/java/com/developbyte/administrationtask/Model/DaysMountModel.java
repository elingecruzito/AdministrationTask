package com.developbyte.administrationtask.Model;

public class DaysMountModel {

    private int day;
    private String day_text;
    private boolean isToday;
    private boolean selected = false;

    public DaysMountModel() {
    }


    public DaysMountModel(int day, String day_text, boolean isToday) {
        this.day = day;
        this.day_text = day_text;
        this.isToday = isToday;
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

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
