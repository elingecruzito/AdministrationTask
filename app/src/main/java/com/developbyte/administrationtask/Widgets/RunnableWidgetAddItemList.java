package com.developbyte.administrationtask.Widgets;

import com.developbyte.administrationtask.Adapters.ListNewTaskAdapter;
import com.developbyte.administrationtask.Model.TasksModel;

public class RunnableWidgetAddItemList implements Runnable{

    private ListNewTaskAdapter listNewTaskAdapter;
    private String name;
    private String date;
    private String hour;

    public void setListNewTaskAdapter(ListNewTaskAdapter listNewTaskAdapter) {
        this.listNewTaskAdapter = listNewTaskAdapter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public void run() {
        listNewTaskAdapter.setTasksModel(new TasksModel(
                name,
                hour,
                date
        ));
    }
}
