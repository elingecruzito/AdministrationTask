package com.developbyte.administrationtask.NewProject;

import android.content.Context;
import android.util.Log;

import com.developbyte.administrationtask.Abstract.AbstractBusinessController;
import com.developbyte.administrationtask.Model.TasksModel;

import java.util.List;

public class NewProjectBusinessController extends AbstractBusinessController
                        implements INewProject.INewProjectTransactionHandler, //MasterBussinesController a BussinesController 
                        INewProject.INewProjectRepresentationDelegate, //ViewController a Businnes 
                        INewProject.INewProjectInformationDelegate{ //Service a BusinessComtroller

    private INewProject.INewProjectRepresentationHandler representationHandler;
    private INewProject.INewProjectInformationHandler informationHandler;
    private INewProject.INewProjectTransactionDelegate transactionDelegate;

    public void setRepresentationHandler(INewProject.INewProjectRepresentationHandler representationHandler) {
        this.representationHandler = representationHandler;
    }

    public void setInformationHandler(INewProject.INewProjectInformationHandler informationHandler) {
        this.informationHandler = informationHandler;
    }

    public void setTransactionDelegate(INewProject.INewProjectTransactionDelegate transactionDelegate){
        this.transactionDelegate = transactionDelegate;
    }

    @Override
    public void startNewProject() {
        representationHandler.showNewProject();
    }

    @Override
    public void createNewProject(String name, List<TasksModel> tasksModelList) {
        informationHandler.createNewProject(name, tasksModelList);
    }
}