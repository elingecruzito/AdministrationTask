package com.developbyte.administrationtask.Model;

public class TasksModel {

    private String task;
    private String project;
    private String hour;
    private String date;
    private int status;

    public static final int STATUS_IN_PROGRESS = 0;
    public static final int STATUS_COMPLETE = 1;

    public TasksModel() {
    }

    public TasksModel(String task, String hour, String date) {
        this.task = task;
        this.hour = hour;
        this.date = date;
        this.status = TasksModel.STATUS_IN_PROGRESS;
    }

    public TasksModel(String task, String project, String hour, String date, int status) {
        this.task = task;
        this.project = project;
        this.hour = hour;
        this.date = date;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
