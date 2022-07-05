package com.developbyte.administrationtask.Model;

public class TasksModel {

    private int id_task;
    private String task;
    private int id_project;
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
    }

    public TasksModel(int id_task, String task, int id_project, String project, String hour, String date, int status) {
        this.id_task = id_task;
        this.task = task;
        this.id_project = id_project;
        this.project = project;
        this.hour = hour;
        this.date = date;
        this.status = status;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
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
