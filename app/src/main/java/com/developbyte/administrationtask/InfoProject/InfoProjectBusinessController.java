package com.developbyte.administrationtask.InfoProject;

import android.util.Log;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;
import com.developbyte.administrationtask.Model.ProjectModel;
import com.developbyte.administrationtask.Model.TasksModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InfoProjectBusinessController extends AbstractBusinessController
                        implements IInfoProject.IInfoProjectTransactionHandler, //MasterBussinesController a BussinesController 
                        IInfoProject.IInfoProjectRepresentationDelegate, //ViewController a Businnes 
                        IInfoProject.IInfoProjectInformationDelegate{ //Service a BusinessComtroller

    private IInfoProject.IInfoProjectRepresentationHandler representationHandler;
    private IInfoProject.IInfoProjectInformationHandler informationHandler;
    private IInfoProject.IInfoProjectTransactionDelegate transactionDelegate;

    private List<TasksModel> tasksModels;

    public void setRepresentationHandler(IInfoProject.IInfoProjectRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(IInfoProject.IInfoProjectInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(IInfoProject.IInfoProjectTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startInfoProject(int id) {
        representationHandler.showInfoProject(id);
    }


    @Override
    public void getDataProject(int id) {
        informationHandler.getDataProject(id);
    }

    @Override
    public void getAllProgressTask(int idProject) {
        informationHandler.getAllProgressTask(idProject);
    }

    @Override
    public void getAllCompleteTask(int idProject) {
       informationHandler.getAllCompleteTask(idProject);
    }

    @Override
    public void createNewTask(TasksModel tasksModel) {
        informationHandler.createNewTask(tasksModel);
    }

    @Override
    public void updateStatusTask(int idtask) {
        informationHandler.updateStatusTask(idtask);
    }

    @Override
    public void deleteTask(int idtask) {
        informationHandler.deleteTask(idtask);
    }

    @Override
    public void setDataProject(ProjectModel project) {
        representationHandler.setDataProject(project);
    }

    @Override
    public void setAllProgressTask(List<TasksModel> progressTask) {
        representationHandler.setAllProgressTask(progressTask);
    }

    @Override
    public void setAllCompleteTask(List<TasksModel> completeTask) {
        representationHandler.setAllCompleteTask(completeTask);
    }

    @Override
    public void setInsertTask(TasksModel task) {
        representationHandler.setInsertTask(task);
    }

    @Override
    public void updateStatusTaskResult(boolean ready) {
        representationHandler.updateStatusTaskResult(ready);
    }

    @Override
    public void deleteTask(boolean ready) {
        representationHandler.deleteTask(ready);
    }
}