package com.developbyte.administrationtask.Model;

public class MonthsModel {

    private String month;
    private boolean isActive;

    public MonthsModel(String month, boolean isActive) {
        this.month = month;
        this.isActive = isActive;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
