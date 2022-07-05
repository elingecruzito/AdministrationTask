package com.developbyte.administrationtask.InfoProject;

import android.util.Log;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;
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
    public void startInfoProject() {
        representationHandler.showInfoProject();
    }


    @Override
    public void getAllProgressTask(int idProject) {
        if(tasksModels == null){
            tasksModels = new ArrayList<>();
        }else{
            tasksModels.clear();
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 999999); i++){
            tasksModels.add(new TasksModel());
        }
        representationHandler.setAllProgressTask(tasksModels);
    }

    @Override
    public void getAllCompleteTask(int idProject) {
        if(tasksModels == null){
            tasksModels = new ArrayList<>();
        }else{
            tasksModels.clear();
        }

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 999999); i++){
            tasksModels.add(new TasksModel());
        }
        representationHandler.setAllCompleteTask(tasksModels);
    }
}