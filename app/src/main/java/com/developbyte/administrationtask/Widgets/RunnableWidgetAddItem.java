package com.developbyte.administrationtask.Widgets;

import com.developbyte.administrationtask.InfoProject.IInfoProject;
import com.developbyte.administrationtask.Model.TasksModel;

public class RunnableWidgetAddItem implements Runnable {

    private TasksModel tasksModel;
    private IInfoProject.IInfoProjectRepresentationDelegate representationDelegate;

    public void setRepresentationDelegate(IInfoProject.IInfoProjectRepresentationDelegate representationDelegate) {
        this.representationDelegate = representationDelegate;
    }

    public void setTasksModel(TasksModel tasksModel) {
        this.tasksModel = tasksModel;
    }

    @Override
    public void run() {
        representationDelegate.createNewTask(tasksModel);
    }
}
